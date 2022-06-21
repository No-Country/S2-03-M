package com.nocountry.travel.mapper;

import com.nocountry.travel.dto.FlightPassengerDTO;
import com.nocountry.travel.entity.Flight;
import com.nocountry.travel.entity.Passenger;
import org.springframework.stereotype.Component;

@Component
public class FlightPassengerMapper {

    public FlightPassengerDTO flightAndPassenger2DTO(Flight flight, Passenger passenger ){

        FlightPassengerDTO flightPassengerDTO= new FlightPassengerDTO();
        flightPassengerDTO.setOriginLocation(flight.getOriginLocation());
        flightPassengerDTO.setDestinationLocation(flight.getDestinationLocation());
        flightPassengerDTO.setCodeFlight(flight.getCodeFlight());
        flightPassengerDTO.setSeat(flight.getSeat());
        flightPassengerDTO.setDuration(flight.getDuration());
        flightPassengerDTO.setDepartureIataCode(flight.getDepartureIataCode());
        flightPassengerDTO.setArrivalIataCode(flight.getArrivalIataCode());
        flightPassengerDTO.setName(passenger.getName());
        flightPassengerDTO.setDocumentNumber(passenger.getDocumentNumber());
        flightPassengerDTO.setNationality(passenger.getNationality());
        return flightPassengerDTO;
    }


}
