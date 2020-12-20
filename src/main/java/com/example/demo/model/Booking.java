package com.example.demo.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter(value=AccessLevel.NONE)
    private Customer customer;


}
