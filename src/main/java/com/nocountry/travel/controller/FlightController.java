package com.nocountry.travel.controller;

import com.nocountry.travel.dto.FlightDTO;
import com.nocountry.travel.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;


    /* @GetMapping("/flights")
    public ResponseEntity<FlightOfferSearch[]> searchFlights(@RequestParam(required = false) String origin,
                                                            @RequestParam(required = false) String destination,
                                                            @RequestParam(required = false) String departDate,
                                                            @RequestParam(required = false) String adults,
                                                            @RequestParam(required = false)String returnDate) throws ResponseException {

        flightService.searchFlight(origin, destination, departDate, adults, returnDate);
        return null;
    }*/

    @PostMapping("/save")
    public ResponseEntity<FlightDTO> saveFlight(@RequestBody FlightDTO flightDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(flightService.save(flightDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFlight(@PathVariable String id, @RequestBody FlightDTO flightDTO){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(flightService.update(flightDTO, id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable String id){
        try{
            flightService.deleteFlight(id);
            return ResponseEntity.status(HttpStatus.OK).body("Vuelo eliminado con exito");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllFlights(){
        return ResponseEntity.status((HttpStatus.OK)).body(flightService.listAll());
    }

    @GetMapping("/getForId/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(flightService.getById(id));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @GetMapping("paged")
    public ResponseEntity<?> getAllPage(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(flightService.getAllPage(pageable));
    }
}
