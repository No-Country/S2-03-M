package com.nocountry.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {
    private String id;
    private String departDate;
    private String returnDate;
    private String originLocation;
    private String destinationLocation;
    private Float price;
    private String currency;
    private String codeFlight;
    private Integer seat;
    private String lastTicketingDate;
    private String duration;
    private String departureIataCode;
    private String arrivalIataCode;
    private String arrivalDate;
   // private ArrayList<String> urlImage = new ArrayList();
}
