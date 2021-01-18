package com.example.demo.service;

import com.example.demo.Error.WrongIdNumber;
import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import com.example.demo.temp.CurrentBooking;
import com.example.demo.temp.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Scope("prototype")
@Slf4j
public class BookingServiceDbImpl implements BookingService {

    private UserService userService;
    private final BookingRepository bookingRepository;

    public BookingServiceDbImpl(UserService userService, BookingRepository bookingRepository) {
        this.userService = userService;
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
            booking.setId(id);
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

    @Override
    public Booking getBookingForLeggedUserById(Integer bookingId) {
        return bookingRepository.findById(bookingId);
    }

    // get all reservations for logger user
    @Override
    @Transactional
    public Collection<Booking> getBookingsForLoggedUser() {
        return bookingRepository.findAllByUserId((userService.getLoggedUserId()));
    }

    // transfer data between temp reservation and Reservation class after check it to save it
    @Override
    @Transactional
    public void saveOrUpdateBooking(CurrentBooking currentBooking) {
        Booking booking = new Booking();

        // get required id user using user services
        booking.setUserId(userService.getLoggedUserId());

        booking.setCheckIn(currentBooking.getCheckIn());
        booking.setCheckOut(currentBooking.getCheckOut());
        booking.setAdults(currentBooking.getAdults());
        booking.setChildren(currentBooking.getChildren());
//        booking.setAllInclusive(currentBooking.getAllInclusive());
        booking.setPrice(currentBooking.getPrice());
        booking.setId(currentBooking.getId());

        bookingRepository.save(booking);
    }

    @Override
    public void deleteReservation(Integer bookingId) {

    }

    @Override
    public CurrentBooking reservationToCurrentReservationById(Integer bookingId) {
        return null;
    }

}
