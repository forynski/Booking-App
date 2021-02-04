package com.example.demo.service;

import com.example.demo.model.Booking;
import com.example.demo.model.User;
import org.springframework.data.domain.Page;

import java.util.List;


public interface BookingService {
    Booking createNewBooking(Booking booking, User user);

    Booking getBookingById(Long id);

    List<Booking> getAllBookings(Integer page, Integer size);

    Booking updateBooking(Booking booking);

    boolean deleteBookingById(Long id);

    List<Booking> getCurrentBookingsByUser(User user);

    Page<Booking> getCurrentBookings(Integer page, Integer size);


}
