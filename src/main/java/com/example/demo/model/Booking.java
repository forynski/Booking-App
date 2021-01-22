package com.example.demo.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Random;

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
    Integer roomNumber;
    double calculatedPrice = 0;

    public double getPrice() {
        long days = DAYS.between(checkIn, checkOut);

        switch (roomClass) {
            case 1:
                price *= 1.0;
                break;
            case 2:
                price *= 1.5;
                break;
            case 3:
                price *= 2.0;
                break;
        }

        double calculatedPrice = (price * adults) * days;
        if (allInclusive) {
            calculatedPrice += 20.00 * days;
        }
        if (children > 0) {
            calculatedPrice += 20.00 * children * days;
        }
        return calculatedPrice;
    }

    public Integer generateRoomNumber() {
        Random random = new Random();
        int roomNumber;
        do {
            roomNumber = random.nextInt(101);
        } while (roomNumber == 0);
        return roomNumber;
    }

    //TODO: DATABASE KEYS
//    @ManyToOne(fetch = FetchType.LAZY)
//    @Getter(value = AccessLevel.NONE)
//    private User user;

}




