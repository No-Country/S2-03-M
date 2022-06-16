package com.nocountry.travel.controller;


import com.nocountry.travel.dto.PassengerDTO;
import com.nocountry.travel.service.PassengersWithFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("passengerFlight")
public class PassengersWithFlightController {

    @Autowired
    private PassengersWithFlight passengersWithFlight;

    @PutMapping("/confirmedReserve/{id}")
    public ResponseEntity<?> confirmedReserve(@PathVariable String id, @RequestBody List<PassengerDTO> passengerDTOS){
        passengersWithFlight.unionPassengersWithFlight(id, passengerDTOS);
        return ResponseEntity.status(HttpStatus.OK).body("Reserva creada con exito!");
    }

    @GetMapping("/myTrips/{id}")
    public ResponseEntity<?> myTrips(){
        return ResponseEntity.status(HttpStatus.OK).body(passengersWithFlight.flightsBasic());
    }


    @GetMapping("/passengersOfTrip/{id}")
    public ResponseEntity<?> getPassengerByIdFlight(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(passengersWithFlight.getPassengerByIdFlight(id));
    }
}
