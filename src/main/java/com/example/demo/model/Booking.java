package com.example.demo.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    double calculatedPrice = 0;

    public double getPrice() {
        long days = DAYS.between(checkIn, checkOut);
        double calculatedPrice = (price * adults) * days;
        if (allInclusive) {
            calculatedPrice += 150.00;
        }
        if (children > 0) {
            calculatedPrice += 20.00 * children * days;
        }
        return calculatedPrice;
    }

//    @ManyToOne(fetch = FetchType.LAZY)
//    @Getter(value=AccessLevel.NONE)
//    private User user;

//    @ManyToMany
//    @JoinTable(
//            name="BOOKING_ROOM",
//            joinColumns = @JoinColumn(name = "BOOKING_ID"),
//            inverseJoinColumns = @JoinColumn(name = "ROOM_ID")
//    )



//    private List<Room> rooms = new ArrayList<>();
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @Getter(value=AccessLevel.NONE)
//    private User user;
//
//    public void addRoom(Room room){
//        rooms.add(room);
//    }

}
