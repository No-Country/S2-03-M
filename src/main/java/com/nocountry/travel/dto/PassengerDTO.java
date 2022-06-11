package com.nocountry.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDTO {

private String documentNumber;
private String nationality;
private String name;
private String dateOfBirth;
private String email;
private Long telephoneNumber;
}

