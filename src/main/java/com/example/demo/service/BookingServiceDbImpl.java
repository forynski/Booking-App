package com.example.demo.service;

import com.example.demo.exception.IdNotFoundException;
import com.example.demo.exception.WrongIdNumber;
import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Scope("prototype")
@Slf4j
public class BookingServiceDbImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceDbImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking createNewBooking(Booking booking) {
        log.info("Creating new booking");
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(Long id) throws WrongIdNumber {
        if (bookingRepository.findById(id).isEmpty()) {
            // log.info("couldn't find any match with this id " + id);
            throw new WrongIdNumber("couldn't find any match with this id" + id);
        } else {
            log.info("Found booking by id " + id);
            return bookingRepository.findById(id).orElseThrow();
        }
    }

    @Override
    public List<Booking> getAllBookings(Integer page, Integer size) {
        log.info("All bookings found");
        return bookingRepository.findAll();
    }

    @Override
    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public boolean deleteBookingById(Long id) throws IdNotFoundException {
        if (bookingRepository.findById(id).isEmpty()) {
            throw new IdNotFoundException("couldn't find any match with this id " + id);
        } else {
            log.info("Booking successfully deleted");
            bookingRepository.deleteById(id);
            return true;
        }
    }
}


