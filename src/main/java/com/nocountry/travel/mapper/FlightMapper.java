package com.nocountry.travel.mapper;

import com.nocountry.travel.dto.FlightDTO;
import com.nocountry.travel.entities.Flight;
import com.nocountry.travel.repositories.FlightRepository;
import com.nocountry.travel.service.FlightService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class FlightMapper {

    private ModelMapper mapper= new ModelMapper();

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepository flightRepository;

    public Flight flightDtoToFlight(FlightDTO flightDTO){
        Flight flight= mapper.map(flightDTO, Flight.class);
        LocalDate localDate= LocalDate.from(LocalDateTime.now());
        flight.setLastTicketingDate(localDate);
        flight.setDepartDate(string2Date(flightDTO.getDepartDate()));
        flight.setArrivalDate(string2Date(flightDTO.getArrivalDate()));
        flight.setReturnDate(string2Date(flightDTO.getReturnDate()));
        /*flight.setOriginLocation(flightDTO.getOriginLocation());
        flight.setDestinationLocation(flightDTO.getDestinationLocation());
        flight.setPrice(flightDTO.getPrice());
        flight.setCurrency(flightDTO.getCurrency());
        flight.setCodeFlight(flightDTO.getCodeFlight());
        flight.setSeat(flightDTO.getSeat());
        flight.setDuration(flightDTO.getDuration());
        flight.setDepartureIataCode(flightDTO.getDepartureIataCode());*/
        return flight;
    }

    private static LocalDate string2Date(String s){
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate= LocalDate.parse(s, formatter);
        return localDate;
    }

    public FlightDTO flight2FlightDTO(Flight flight){
        FlightDTO flightDTO= mapper.map(flight, FlightDTO.class);
        flightDTO.setDepartDate(flight.getDepartDate().toString());
        flightDTO.setReturnDate(flight.getReturnDate().toString());
        flightDTO.setArrivalDate(flight.getArrivalDate().toString());
        flightDTO.setLastTicketingDate(flight.getLastTicketingDate().toString());
        return flightDTO;
    }

    public List<FlightDTO> flightList2FlightDTOList(List<Flight> flights){
        List<FlightDTO> flightDTOS= new ArrayList<>();
        for (Flight flight: flights) {
            flightDTOS.add(flight2FlightDTO(flight));
        }
        return flightDTOS;
    }

    public FlightDTO flightUpdate2DTO(String id, FlightDTO flightDTO)throws Exception{
        Flight flight= flightService.getById(id);
        flight.setPrice(flightDTO.getPrice());
        flight.setDuration(flightDTO.getDuration());
        flight.setCodeFlight(flightDTO.getCodeFlight());
        flight.setSeat(flightDTO.getSeat());
        flight.setCurrency(flightDTO.getCurrency());
        flight.setDepartDate(string2Date(flightDTO.getDepartDate()));
        flight.setReturnDate(string2Date(flightDTO.getReturnDate()));
        flight.setArrivalIataCode(flightDTO.getArrivalIataCode());
        flight.setDepartureIataCode(flightDTO.getDepartureIataCode());
        flight.setArrivalDate(string2Date(flightDTO.getArrivalDate()));
        flightRepository.save(flight);
        return flight2FlightDTO(flight);
    }
}
