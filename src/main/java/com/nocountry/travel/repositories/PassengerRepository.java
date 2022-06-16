package com.nocountry.travel.repositories;

import com.nocountry.travel.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, String> {

    @Query("select p from Passenger p where p.flight.id = :id")
    List<Passenger> listByIdFlifht(@Param("id") String id);


}
