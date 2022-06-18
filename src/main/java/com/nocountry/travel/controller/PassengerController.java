package com.nocountry.travel.controller;

import com.nocountry.travel.dto.PassengerDTO;
import com.nocountry.travel.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    private PassengerService  passengerService;

    @PostMapping("/save")
    public ResponseEntity<?> savePassenger(@RequestBody PassengerDTO passengerDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(passengerService.save(passengerDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePassenger(@RequestBody PassengerDTO passengerDTO, @PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(passengerService.update(id, passengerDTO));
    }

    @GetMapping("all")
    public ResponseEntity<?> allPassangers(){
        return ResponseEntity.status(HttpStatus.OK).body(passengerService.listAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(passengerService.findById(id));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deletePassangerById(@PathVariable String id){
        passengerService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pasajero eliminado con exito");
    }
}
