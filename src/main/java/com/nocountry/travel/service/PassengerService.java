package com.nocountry.travel.service;

import com.nocountry.travel.dto.PassengerDTO;
import com.nocountry.travel.entities.Passenger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PassengerService {

    PassengerDTO save(PassengerDTO passengerDTO);

    PassengerDTO update(String id, PassengerDTO passengerDTO);

    void deleteById(String id);

    List<PassengerDTO> listAll();

    Passenger getById(String id);

    PassengerDTO findById(String id);


}
