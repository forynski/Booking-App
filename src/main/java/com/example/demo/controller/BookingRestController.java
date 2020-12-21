package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(path = "/booking/booking")
@Slf4j
public class BookingRestController {

    private final BookingService bookingService;

    public BookingRestController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<?> createNewBooking(@RequestBody Booking booking) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.createNewBooking(booking));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        if (Objects.nonNull(booking)) {
            return ResponseEntity.ok(booking);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> getBookings(@RequestParam(required = false) Integer page,
                                         @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(bookingService.getAllBookings(page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBookingById(@PathVariable Long id, @RequestBody Booking booking) {
        Booking updateBooking = bookingService.updateBookingById(id, booking);
        if (Objects.nonNull(updateBooking)) {
            return ResponseEntity.ok(booking);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeBookingById(@PathVariable Long id) {
        if (bookingService.removeBookingById(id)) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
