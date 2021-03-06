package com.nocountry.travel.service;

import com.nocountry.travel.dto.FlightBasicDTO;
import com.nocountry.travel.dto.PassengerBasicDTO;
import com.nocountry.travel.dto.PassengerDTO;
import com.nocountry.travel.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PassengersWithFlight {

    void unionPassengersWithFlightAndUser(int idUser,String id, List<PassengerDTO> passengerDTOS);

    List<FlightBasicDTO> flightsBasic();

    List<PassengerBasicDTO> getPassengerByIdFlight(String id);

    List<FlightBasicDTO> flightByIdUser(int id);

    Usuario getById(int id);

}
