package com.nocountry.travel.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlightBasicDTO {

    private String id;
    private String returnDate;
    private String originLocation;
    private String destinationLocation;
    private String departDate;
    private Integer amountPassengers;
}
