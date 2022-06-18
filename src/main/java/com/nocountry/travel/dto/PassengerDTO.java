package com.nocountry.travel.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDTO {

private String documentNumber;
private String nationality;
private String name;
private String dateOfBirth;
private String email;
private Long telephoneNumber;
//private List<FlightDTO> flightDTOS;
}

