package com.example.demo.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "booking")
public class Booking {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private Integer adults;
    @NotNull
    private Integer children;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkIn;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOut;
    @NotNull
    private Boolean allInclusive;
    @NotNull
    private Double price = 100.00;

    Integer roomClass;

    double calculatedPrice = 0;

    public double getPrice() {
        long days = DAYS.between(checkIn, checkOut);

        if (roomClass == 1) {
            price *= 1.0;
        }
        if (roomClass == 2) {
            price *= 1.5;
        }
        if (roomClass == 3) {
            price *= 2.0;
        }
        double calculatedPrice = (price * adults) * days;
        if (allInclusive) {
            calculatedPrice *= 0.2;
        }
        if (children > 0) {
            calculatedPrice += 20.00 * children * days;
        }
        return calculatedPrice;
    }

}


//    @ManyToOne(fetch = FetchType.LAZY)
//    private Room room;



