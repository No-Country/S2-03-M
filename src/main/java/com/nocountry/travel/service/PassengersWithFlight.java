package com.nocountry.travel.service;

import com.nocountry.travel.dto.FlightBasicDTO;
import com.nocountry.travel.dto.PassengerBasicDTO;
import com.nocountry.travel.dto.PassengerDTO;
import com.nocountry.travel.entities.Flight;
import com.nocountry.travel.entities.Passenger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PassengersWithFlight {

    void unionPassengersWithFlight(String id, List<PassengerDTO> passengerDTOS);

    List<FlightBasicDTO> flightsBasic();

    List<PassengerBasicDTO> getPassengerByIdFlight(String id);

}
