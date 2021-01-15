package com.example.demo.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
    private Integer rooms;
    @NotNull
    private String destination;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkIn;
    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime checkInTime;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOut;
    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime checkOutTime;
    @NotNull
    private Boolean allInclusive;
    @NotNull
    private Double calculatedPrice;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @Getter(value=AccessLevel.NONE)
//    private Customer customer;

    @ManyToMany
    @JoinTable(
            name="BOOKING_HOTEL",
            joinColumns = @JoinColumn(name = "BOOKING_ID"),
            inverseJoinColumns = @JoinColumn(name = "HOTEL_ID")
    )
    private List<Hotel> hotels = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter(value=AccessLevel.NONE)
    private Customer customer;

    public void addHotel(Hotel hotel){
        hotels.add(hotel);
    }

}
