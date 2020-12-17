package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String hotelName;
    @NotNull
    private String locationCity;
    @NotNull
    private String locationCountry;
    @NotNull
    @Min(value = 1, message = "Star rating must not be less than 0")
    @Max(value = 5, message = "Star rating must not be greater than 5")
    private Integer starRating;
    @NotNull
    private Integer rooms;
    @NotNull
    private Integer costPerNight;

}

