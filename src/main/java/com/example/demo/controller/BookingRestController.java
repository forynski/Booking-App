package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.User;
import com.example.demo.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/booking/booking")
@Slf4j
public class BookingRestController {

    private final BookingService bookingService;

    public BookingRestController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<?> createNewBooking(@RequestBody Booking booking, User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.createNewBooking(booking, user));
    }

    @GetMapping
    public ResponseEntity<?> getBookings(@RequestParam(required = false) Integer page,
                                         @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(bookingService.getAllBookings(page, size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        if (bookingService.deleteBookingById(id)) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.badRequest().build();
    }


    //    //SEARCH:
//    @GetMapping("/hotel/{locationCity}")
//    public ResponseEntity<?> getHotelsByCity(@PathVariable String locationCity) {
//        List<Hotel> hotels =hotelService.findHotelByLocationCity(locationCity);
//        return ResponseEntity.notFound().build();
//    }
}
