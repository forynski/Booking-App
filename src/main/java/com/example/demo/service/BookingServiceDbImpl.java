package com.example.demo.service;

import com.example.demo.Error.WrongIdNumber;
import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

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
    public Booking updateBookingById(Long id, Booking booking) throws WrongIdNumber {
        if (bookingRepository.findById(id).isEmpty()) {
            throw new WrongIdNumber("couldn't find any match with this id" + id);
        } else {
            log.info("Booking data updated");
            booking.setBookingId(id);
            return bookingRepository.save(booking);
        }
    }

    @Override
    public boolean removeBookingById(Long id) throws WrongIdNumber {
        if (bookingRepository.findById(id).isEmpty()) {
            throw new WrongIdNumber("couldn't find any match with this id" + id);
        } else {
            log.info("Booking deleted");
            bookingRepository.deleteById(id);
            return true;
        }
    }
}
