package com.example.demo.service;

import com.example.demo.model.Booking;

import java.util.List;

public interface BookingService {
    Booking createNewBooking(Booking booking);

    Booking getBookingById(Long id);

    List<Booking> getAllBookings(Integer page, Integer size);

    Booking updateBookingById(Long id, Booking booking);

    boolean removeBookingById(Long id);
}
