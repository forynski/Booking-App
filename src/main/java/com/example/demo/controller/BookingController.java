package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.service.BookingService;
import com.example.demo.service.RoomService;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
public class BookingController {

    private final BookingService bookingService;
    private final RoomService roomService;

    public BookingController(BookingService bookingService, RoomService roomService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
    }

    // MODEL MAP SENDING MODEL TO VIEWS
    @GetMapping("/booking/{id}")
    public String booking(ModelMap modelMap, @PathVariable Long id) {
        modelMap.addAttribute("booking", bookingService.getBookingById(id));
        return "one-booking";
    }

    @GetMapping(path = "/booking")
    public String booking(ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
        List<Booking> bookingPage = bookingService.getAllBookings(1, 100);
        modelMap.addAttribute("bookingList", bookingService.getAllBookings(1, 100));
        modelMap.addAttribute("bookingPage", bookingPage);

        boolean isAuthorizedUserAdmin = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUserAdmin);
        modelMap.addAttribute("isUserLogged", true);
        modelMap.addAttribute("isAuthorizedUserAdmin", true);
        return "booking";
    }

    @GetMapping(path = "/booking/add")
    public String showBookingAdd(ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
        modelMap.addAttribute("booking", new Booking());

        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUser = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUser);
        }
        return "booking-add";
    }

    @PostMapping(path = "/booking/add")
    public String addBooking(@Valid @ModelAttribute("booking") Booking booking, ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {

        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUser = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUser);
        }

        Booking newOne = bookingService.createNewBooking(booking);
        modelMap.addAttribute("newOne", newOne);
        return "redirect:/booking";
    }

    // GET EDIT BOOKING
    @GetMapping(value = "/booking/update/{id}")
    public String showBookingToUpdate(ModelMap modelMap, @PathVariable Long id, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
//        modelMap.addAttribute("booking", bookingService.getBookingById(id));

        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUser = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUser);
        }
        modelMap.addAttribute("booking", bookingService.getBookingById(id));
        return "booking-update";
    }

//    // POST EDIT BOOKING
//    @PostMapping("/booking/update")
//    public String updateBookingById(@Valid @ModelAttribute("booking") ModelMap modelMap, Booking booking, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
//        modelMap.addAttribute("booking");
//        boolean isUserLogged = Objects.nonNull(authenticationUser);
//        modelMap.addAttribute("isUserLogged", isUserLogged);
//        if (isUserLogged) {
//            boolean isAuthorizedUser = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
//                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
//            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUser);
//        }
//        return "redirect:/booking/" + booking.getId();
//
//

    @PostMapping("/booking/update")
    public String updateBookingById(ModelMap modelMap, @Valid @ModelAttribute("booking") Booking booking, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {

        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUser = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUser);
        }
        bookingService.updateBooking(booking);
        return "redirect:/booking/" + booking.getId();
    }

        //    //SEARCH
//    @RequestMapping(value = "hotels", method = RequestMethod.GET)
//    public String findHotelByLocationCity(@RequestParam (value = "search", required = false) String locationCity, Model model) {
//        model.addAttribute("search", hotelService.findHotelByLocationCity(locationCity));
//        return "hotels";
//    }


    }

