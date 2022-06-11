package com.nocountry.travel.service.impl;

import com.nocountry.travel.dto.PassengerDTO;
import com.nocountry.travel.entities.Passenger;
import com.nocountry.travel.exception.ParamNotFound;
import com.nocountry.travel.mapper.PassengerMapper;
import com.nocountry.travel.repositories.PassengerRepository;
import com.nocountry.travel.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {


    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private PassengerMapper passengerMapper;

    @Override
    public PassengerDTO save(PassengerDTO passengerDTO) {
        Passenger passenger= passengerMapper.passengerDTO2Passenger(passengerDTO);
        Passenger passengerSaved= passengerRepository.save(passenger);
        return passengerMapper.passenger2PassengerDTO(passengerSaved);
    }

    @Override
    public PassengerDTO update(String id, PassengerDTO passengerDTO) {
        Passenger passenger= this.getById(id);
        passenger.setEmail(passengerDTO.getEmail());
        passenger.setTelephoneNumber(passengerDTO.getTelephoneNumber());
        return passengerMapper.passenger2PassengerDTO(passengerRepository.save(passenger));
    }

    @Override
    public void deleteById(String id) {
        passengerRepository.deleteById(id);
    }

    @Override
    public List<PassengerDTO> listAll() {
        return passengerMapper.listPassenger2DTOList(passengerRepository.findAll());
    }

    @Override
    public Passenger getById(String id) {
        if(!(id==null)){
            Optional<Passenger> optional= passengerRepository.findById(id);
            if (optional.isPresent()){
                return optional.get();
            }else {
                throw new ParamNotFound("No existe pasajero con el numero de documento solicitado: "+id);
            }
        }else {
            throw new ParamNotFound("El id es nulo");
        }
    }

    @Override
    public PassengerDTO findById(String id) {
        return passengerMapper.passenger2PassengerDTO(this.getById(id));
    }
}
