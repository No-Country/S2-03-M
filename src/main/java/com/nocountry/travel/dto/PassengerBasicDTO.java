package com.nocountry.travel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassengerBasicDTO {

    private String documentNumber;

    private String nationality;

    private String name;

    private String dateOfBirth;

    private String email;

    private Long telephoneNumber;
}
