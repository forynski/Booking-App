package com.example.demo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue
    // TODO: zmienic zeby nie wrzucalo do jednej tabeli customera i hotelu
    private Long id;
    @NotNull
    private String hotelName;
    @NotNull
    private String locationCity;
    @NotNull
    private String locationCountry;
    @NotNull
    @Min(value = 0, message = "Star rating must not be less than 0")
    @Max(value = 5, message = "Star rating must not be greater than 5")
    private Integer starRating;
    @NotNull
    private Integer rooms;
    @NotNull
    private Integer costPerNight;

//    @ManyToMany(mappedBy = "hotels")
//    @Setter(value = AccessLevel.NONE)
//    private List<Booking> bookings = new ArrayList<>();

//    public void addBooking(Booking booking) {
//        bookings.add(booking);
//    }

}

