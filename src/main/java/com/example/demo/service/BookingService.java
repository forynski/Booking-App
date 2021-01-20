package com.example.demo.service;

import com.example.demo.exception.IdNotFoundException;
import com.example.demo.model.Booking;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BookingService {
    Booking createNewBooking(Booking booking);

    Booking getBookingById(Long id);

    List<Booking> getAllBookings(Integer page, Integer size);

    boolean removeBookingById(Long id);

    Booking updateBooking(Booking booking);

}
