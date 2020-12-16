package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String hotelName;
    @NotNull
    private String location;
    @NotNull
    @Min(value = 1, message = "Star rating must not be less than 0")
    @Max(value = 5, message = "Star rating must not be greater than 5")
    private Integer starRating;
    // TODO: dodać @Valid na gwiazdki od 1-5
    @NotNull
    private Integer rooms;
    @NotNull
    private Integer costPerNight;

}

