package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "booking")
public class Booking {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private Integer numberInParty;
    @NotNull
    private String selectedHotelName;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkIn;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOut;


}
