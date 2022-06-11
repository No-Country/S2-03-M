package com.nocountry.travel.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class Passenger {
    @Id
    private String documentNumber;

    private String nationality;

    private String name;

    private LocalDate dateOfBirth;

    private String email;

    private Long telephoneNumber;
}
