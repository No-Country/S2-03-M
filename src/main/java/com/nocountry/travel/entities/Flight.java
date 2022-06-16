package com.nocountry.travel.entities;

import com.nocountry.travel.entities.base.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "flight")
@SQLDelete(sql = "UPDATE flight SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Flight extends BaseEntity {

    //@Min(value = 0)
    //@Max(value = 5)
    //private int rating;

    //private String description;


    @Column(name = "return_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate returnDate;


    private String originLocation;

    private String destinationLocation;

    //private String cabin;

    private Float price;

    private String currency;

    private String codeFlight;

    private Integer seat;

    @Column(name = "ticketing_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate lastTicketingDate;

    private String duration;

    private String departureIataCode;

    private String arrivalIataCode;

    @Column(name = "depart_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate departDate;

    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @OneToMany(mappedBy = "flight" ,cascade = CascadeType.ALL )
    @ToString.Exclude
    private List<Passenger> passengers= new ArrayList<>();

    public void addPassenger(Passenger passenger){
        passengers.add(passenger);
    }

    public void removePassenger(Passenger passenger){
        passengers.remove(passenger);
    }




   // @Column(name = "url_image")
    //private ArrayList<String> urlImage = new ArrayList();

}
