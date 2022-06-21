package com.nocountry.travel.repository;

import com.nocountry.travel.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {

    @Query(value = "select * from Flight" , countQuery = "select count(*) from Flight" , nativeQuery = true)
      Page<Flight> findAllPage(Pageable pageable);

    @Query("select f from Flight f where f.user.id = :id")
    List<Flight> flightByIdUser(@Param("id") int id);
}
