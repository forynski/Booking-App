package com.example.demo.temp;

import com.example.demo.model.Booking;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class CurrentBooking {

    // temp class to filter data and get it from controller to database using services
    //  current reservation fields and annotate to get the required data

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private Long id;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private Integer adults;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private Integer children;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private LocalDate checkIn;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private LocalDate checkOut;

    private @NotNull(message = "is required") @Size(min = 1, message = "is required") Booking allInclusive;

    //TODO: dodać pole/metode PRICE

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private Integer userId;

    // current reservation super and fields constructors

    public CurrentBooking() {
    }

    public CurrentBooking(@NotNull(message = "is required") @Size(min = 1, message = "is required") Long id,
                          @NotNull(message = "is required") @Size(min = 1, message = "is required") Integer adults,
                          @NotNull(message = "is required") @Size(min = 1, message = "is required") Integer children,
                          @NotNull(message = "is required") @Size(min = 1, message = "is required") LocalDate checkIn,
                          @NotNull(message = "is required") @Size(min = 1, message = "is required") LocalDate checkOut,
                          @NotNull(message = "is required") @Size(min = 1, message = "is required") Booking allInclusive,
                          @NotNull(message = "is required") @Size(min = 1, message = "is required") Integer userId) {
        super();
        this.id = id;
        this.adults = adults;
        this.children = children;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.allInclusive = allInclusive;
        this.userId = userId;
    }


    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public Integer getAdults() {
        return adults;
    }

    public Integer getChildren() {
        return children;
    }

    public Double getPrice() {
        return getPrice();
    }

    public Long getId() {
        return getId();
    }
}

