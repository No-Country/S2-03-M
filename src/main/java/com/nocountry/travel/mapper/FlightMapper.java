package com.nocountry.travel.mapper;

import com.nocountry.travel.dto.FlightBasicDTO;
import com.nocountry.travel.dto.FlightDTO;
import com.nocountry.travel.entity.Flight;
import com.nocountry.travel.exception.ParamNotFound;
import com.nocountry.travel.repository.FlightRepository;
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

    @Autowired
    private PassengerMapper passengerMapper;

    public Flight flightDtoToFlight(FlightDTO flightDTO){
        Flight flight= mapper.map(flightDTO, Flight.class);
        LocalDateTime localDate= LocalDateTime.from(LocalDateTime.now());
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

    private static LocalDateTime string2Date(String s){
        if (s==null){
            throw  new ParamNotFound("La fecha es nula o no corresponde a un formato date: "+s);

        }else{
            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a'");
            LocalDateTime localDate= LocalDateTime.parse(s, formatter);
            return localDate;
        }
    }

    public FlightDTO flight2FlightDTO(Flight flight){
        FlightDTO flightDTO= mapper.map(flight, FlightDTO.class);
        flightDTO.setDepartDate(flight.getDepartDate().toString());
        flightDTO.setReturnDate(flight.getReturnDate().toString());
        flightDTO.setArrivalDate(flight.getArrivalDate().toString());
        flightDTO.setLastTicketingDate(flight.getLastTicketingDate().toString());
        //flightDTO.setPassengerDTOS(passengerMapper.listPassenger2DTOList(flight.getPassengers()));
        /*if(loadPassengers){
            flightDTO.setPassengerDTOS(passengerMapper.listPassenger2DTOList(flight.getPassengers()));
        }*/
        return flightDTO;
    }

    public List<FlightDTO> flightList2FlightDTOList(List<Flight> flights){
        List<FlightDTO> flightDTOS= new ArrayList<>();
        for (Flight flight: flights) {
            flightDTOS.add(flight2FlightDTO(flight));
        }
        return flightDTOS;
    }

    public FlightDTO flightUpdate2DTO(String id, FlightDTO flightDTO){
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

    public FlightBasicDTO flight2BasicDTO(Flight flight){
        FlightBasicDTO flightBasicDTO= new FlightBasicDTO();
        flightBasicDTO.setId(flight.getId());
        flightBasicDTO.setDepartDate(flight.getDepartDate().toString());
        flightBasicDTO.setReturnDate(flight.getReturnDate().toString());
        flightBasicDTO.setOriginLocation(flight.getOriginLocation());
        flightBasicDTO.setDestinationLocation(flight.getDestinationLocation());
        flightBasicDTO.setAmountPassengers(flight.getPassengers().size());
        return flightBasicDTO;
    }
}
