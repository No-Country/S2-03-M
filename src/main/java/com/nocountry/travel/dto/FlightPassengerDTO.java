package com.nocountry.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightPassengerDTO {

    private String originLocation;
    private String destinationLocation;
    private String codeFlight;
    private Integer seat;
    private String duration;
    private String departureIataCode;
    private String arrivalIataCode;
    private String departDate;
    private String arrivalDate;
    private String name;
    private String documentNumber;
    private String nationality;
}
