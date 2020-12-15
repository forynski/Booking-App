package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.Errors;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping(path = "/booking")
    public String booking(ModelMap modelMap) {
        List<Booking> bookingList = bookingService.getAllBookings();
        // TODO: model view to create
        return "booking";
    }

    @GetMapping(path = "/booking/add")
    public String showBookingAdd(ModelMap modelMap) {
        modelMap.addAttribute("bookind", new Booking());
        // TODO: model view to create
        return "booking-add";
    }

    @PostMapping(path = "/bookind/add")
    public String addBooking(@Valid @ModelAttribute("booking") Booking booking, final Errors errors) {
        if (errors.hasErrors()) {
            return "booking-add";
        }
        bookingService.createNewBooking(booking);
        return "booking";
    }
}
