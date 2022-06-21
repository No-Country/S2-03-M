package com.nocountry.travel.service.impl;

import com.nocountry.travel.dto.FlightBasicDTO;
import com.nocountry.travel.dto.PassengerBasicDTO;
import com.nocountry.travel.dto.PassengerDTO;
import com.nocountry.travel.entity.Flight;
import com.nocountry.travel.entity.Passenger;
import com.nocountry.travel.entity.Usuario;
import com.nocountry.travel.exception.ParamNotFound;
import com.nocountry.travel.mapper.FlightMapper;
import com.nocountry.travel.mapper.PassengerMapper;
import com.nocountry.travel.repository.FlightRepository;
import com.nocountry.travel.repository.PassengerRepository;
import com.nocountry.travel.repository.UsuarioRepository;
import com.nocountry.travel.service.FlightService;
import com.nocountry.travel.service.PassengerService;
import com.nocountry.travel.service.PassengersWithFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengersWithFlightImpl implements PassengersWithFlight {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private FlightService flightService;

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private PassengerMapper passengerMapper;

    @Autowired
    private FlightMapper flightMapper;

    @Override
    public void unionPassengersWithFlightAndUser(int idUser,String id, List<PassengerDTO> passengerDTOS) {

        Flight flight = flightService.getById(id);
        Usuario user= this.getById(idUser);
        for (PassengerDTO dto : passengerDTOS) {
            Passenger passenger = passengerMapper.passengerDTO2Passenger(dto);
            passenger.setFlight(flight);
            Passenger passengerSaved = passengerRepository.save(passenger);
            flight.addPassenger(passengerSaved);
            Flight flightSaved= flightRepository.save(flight);
            user.addFlight(flightSaved);
            usuarioRepository.save(user);
            System.out.println("TODO OK");
        }
    }


    @Override
    public List<FlightBasicDTO> flightsBasic() {
        List<Flight> flights = flightRepository.findAll();
        List<FlightBasicDTO> flightBasicDTOS = new ArrayList<>();
        if (!flights.isEmpty()) {
            for (Flight flight : flights) {
                flightBasicDTOS.add(flightMapper.flight2BasicDTO(flight));
            }
            return flightBasicDTOS;
        } else {
            throw new ParamNotFound("No tiene vuelos reservados");
        }
    }

    @Override
    public List<PassengerBasicDTO> getPassengerByIdFlight(String id) {

        List<Passenger> passengers= passengerRepository.listByIdFlifht(id);
        List<PassengerBasicDTO> passengerBasicDTOS= new ArrayList<>();
        for (Passenger passenger:passengers) {
            passengerBasicDTOS.add(passengerMapper.passenger2BasicDTO(passenger));
        }
        return passengerBasicDTOS;
    }

    @Override
    public List<FlightBasicDTO> flightByIdUser(int id) {
        List<FlightBasicDTO> flightBasicDTOS= new ArrayList<>();
        if (id!=0){
            List<Flight> flights= flightRepository.flightByIdUser(id);
            if(!flights.isEmpty()){
                for (Flight flight:flights) {
                    flightBasicDTOS.add(flightMapper.flight2BasicDTO(flight));
                }
            }else {
                new ParamNotFound("El usuario no tiene viajes reservados");
            }
        }else{
            new ParamNotFound("El id es nulo");
        }
        return flightBasicDTOS;
    }

    @Override
    public Usuario getById(int id) {
        if (id!=0){
            Optional<Usuario> op=(usuarioRepository.getById(id));
            if (!op.isEmpty()){
                return op.get();
            }else {
                new ParamNotFound("No exite Usuario con el ID: "+id);
            }
        }else {
            new ParamNotFound("El id es nulo");
        }
        return null;
    }


}