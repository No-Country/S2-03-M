package com.nocountry.travel.service;

import com.nocountry.travel.dto.FlightDTO;
import com.nocountry.travel.entities.Flight;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FlightService {

    //FlightOfferSearch[] searchFlight (String origin, String destination, String departDate, String adults, String returnDate) throws ResponseException;

    FlightDTO save(FlightDTO flightDTO);

   FlightDTO update(FlightDTO flightDTO, String id) throws Exception;

   void deleteFlight(String id)throws Exception;

   List<FlightDTO> listAll();

   Flight getById(String id) throws Exception;

   Page<Flight> getAllPage(Pageable pageable);


}
