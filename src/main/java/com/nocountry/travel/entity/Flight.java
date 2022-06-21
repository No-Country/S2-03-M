package com.nocountry.travel.entity;

import com.nocountry.travel.entity.base.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm a'")
    private LocalDateTime returnDate;


    private String originLocation;

    private String destinationLocation;

    //private String cabin;

    private Float price;

    private String currency;

    private String codeFlight;

    private Integer seat;

    @Column(name = "ticketing_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm a'")
    private LocalDateTime lastTicketingDate;

    private String duration;

    private String departureIataCode;

    private String arrivalIataCode;

    @Column(name = "depart_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm a'")
    private LocalDateTime departDate;

    @Column(name = "arrival_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm a'")
    private LocalDateTime arrivalDate;

    @OneToMany(mappedBy = "flight" ,cascade = CascadeType.ALL )
    @ToString.Exclude
    private List<Passenger> passengers= new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "flight_user", joinColumns = @JoinColumn(name= "id_Flight"), inverseJoinColumns = @JoinColumn(name = "id_User"))
    @ToString.Exclude
    private Usuario user;

    public void addPassenger(Passenger passenger){
        passengers.add(passenger);
    }

    public void removePassenger(Passenger passenger){
        passengers.remove(passenger);
    }




   // @Column(name = "url_image")
    //private ArrayList<String> urlImage = new ArrayList();

}
