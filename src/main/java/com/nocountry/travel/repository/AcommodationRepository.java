package com.nocountry.travel.repository;

import com.nocountry.travel.model.Acommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcommodationRepository extends JpaRepository<Acommodation, String> {
}
