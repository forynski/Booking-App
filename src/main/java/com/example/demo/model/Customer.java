package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "customer")
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Boolean isAdult;

    // TODO ustawic pola customera, nie wiem czy te ponizsze sa potrzebne, czy wystarcza w "booking"
//    @NotNull
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate checkIn;
//    @NotNull
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate CheckOut;

}
