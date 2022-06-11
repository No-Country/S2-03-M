package com.nocountry.travel.repositories;

import com.nocountry.travel.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, String> {


}
