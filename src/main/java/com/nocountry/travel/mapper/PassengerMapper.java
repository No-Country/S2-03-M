package com.nocountry.travel.mapper;


import com.nocountry.travel.dto.PassengerDTO;
import com.nocountry.travel.entities.Passenger;
import com.nocountry.travel.exception.ParamNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class PassengerMapper {

    private ModelMapper mapper= new ModelMapper();

    public static LocalDate string2Date(String s){
        if(s==null){
            throw new ParamNotFound("La fecha es nula o no corresponde a un formato Date: "+s);
        }else{
            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(s, formatter);
            return date;
            }
        }

    public Passenger passengerDTO2Passenger(PassengerDTO passengerDTO){
        Passenger passenger= mapper.map(passengerDTO, Passenger.class);
        passenger.setDateOfBirth(string2Date(passengerDTO.getDateOfBirth()));
        return passenger;
    }

    public PassengerDTO passenger2PassengerDTO(Passenger passenger){
        PassengerDTO passengerDTO= mapper.map(passenger, PassengerDTO.class);
        passengerDTO.setDateOfBirth(passenger.getDateOfBirth().toString());
        return passengerDTO;
    }

    public List<PassengerDTO> listPassenger2DTOList(List<Passenger> passengerList){
        List<PassengerDTO> passengerDTOS= new ArrayList<>();
        for (Passenger passenger: passengerList) {
            passengerDTOS.add(this.passenger2PassengerDTO(passenger));
        }
        return passengerDTOS;
    }
}
