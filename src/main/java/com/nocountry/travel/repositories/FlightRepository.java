package com.nocountry.travel.repositories;

import com.nocountry.travel.entities.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {

    @Query(value = "select * from Flight" , countQuery = "select count(*) from Flight" , nativeQuery = true)
      Page<Flight> findAllPage(Pageable pageable);
}
