package com.nocountry.travel.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {
    private String id;
    @NotBlank(message = "La fecha de partida no debe ser nula o estar vacia")
    private String departDate;

    private String returnDate;

    @NotBlank(message = "La ciudad de origen no debe ser nula o estar vacia")
    private String originLocation;

    private String destinationLocation;
    private Float price;
    private String currency;
    private String codeFlight;
    private Integer seat;
    private String lastTicketingDate;
    private String duration;

    @NotBlank(message = "El aeropuerto de salida no puede ser nulo o estar vacio")
    private String departureIataCode;

    @NotBlank(message = "El aeropuesto de llegada no puede ser nulo o estar vacio")
    private String arrivalIataCode;

    private String arrivalDate;

    private List<PassengerDTO> passengerDTOS=new ArrayList<>();
   // private ArrayList<String> urlImage = new ArrayList();
}
