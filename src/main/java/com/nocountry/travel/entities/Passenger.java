package com.nocountry.travel.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "passenger")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Passenger  {
    @Id
    private String documentNumber;

    private String nationality;

    private String name;

    private LocalDate dateOfBirth;

    private String email;

    private Long telephoneNumber;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = Passenger.class)
    @JoinTable(name = "passenger_flight",joinColumns = @JoinColumn(name = "document_Number"), inverseJoinColumns = @JoinColumn(name = "id_flight"))
    private List<Flight> flights= new ArrayList<>();
}
