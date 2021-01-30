package com.example.demo.service;

import com.example.demo.exception.IdNotFoundException;
import com.example.demo.exception.WrongIdNumber;
import com.example.demo.model.Booking;
import com.example.demo.model.User;
import com.example.demo.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Scope("prototype")
@Slf4j
public class BookingServiceDbImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceDbImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    @Override
    public Booking createNewBooking(Booking booking, User user) {
        //TODO: ADD USER TO BOOKING
        log.info("Creating new booking");

        user.addBooking(booking);
        booking.setUser(user);
        return bookingRepository.save(booking);
    }


    @Override
    public Booking getBookingById(Long id) throws WrongIdNumber {
        if (bookingRepository.findById(id).isEmpty()) {
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
        log.info("Booking successfully updated");
        return bookingRepository.save(booking);
    }


    @Override
    public boolean deleteBookingById(Long id) throws IdNotFoundException {
        if (bookingRepository.findById(id).isEmpty()) {
            throw new IdNotFoundException("Couldn't find any match with this id " + id);
        } else {
            log.info("Booking successfully deleted");
            bookingRepository.deleteById(id);
            return true;
        }
    }

    //TESTING
    @Override
    public List<Booking> getCurrentBookingsByUser(User user) {
        if (Objects.nonNull(user)) {
            return new ArrayList<>(user.getBookings());
        }
        return null;
    }


//    public long calculateBookingCost(Booking booking) {
//        Double price = 100.00;
//        long days = DAYS.between(booking.getCheckIn(), booking.getCheckOut());
//        long bookingCost = booking.getRoomClass() * days;
//        if (booking.getAllInclusive()) {
//            bookingCost += 20.00 * days;
//        }
//        if (booking.getChildren() > 0) {
//            bookingCost += 20 * booking.getChildren() * days;
//        }
//        return bookingCost;
//    }


}
