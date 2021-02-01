package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.BookingsManagement;
import com.example.demo.model.User;
import com.example.demo.service.BookingService;
import com.example.demo.service.BookingsManagementService;
import com.example.demo.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
public class BookingController {

    private final BookingService bookingService;
//    private final RoomService roomService;
    private final UserService userService;

    public BookingController(BookingService bookingService, UserService userService) {
        this.bookingService = bookingService;
//        this.roomService = roomService;
        this.userService = userService;
    }

    // MODEL MAP SENDING MODEL TO VIEWS
    @GetMapping("/booking/{id}")
    public String booking(ModelMap modelMap, @PathVariable Long id, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
        modelMap.addAttribute("booking", bookingService.getBookingById(id));

        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUser = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUser);
        }
        return "one-booking";
    }

    @GetMapping(path = "/booking")
    public String booking(ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
        List<Booking> bookingPage = bookingService.getAllBookings(1, 100);
        modelMap.addAttribute("bookingList", bookingService.getAllBookings(1, 100));
        modelMap.addAttribute("bookingPage", bookingPage);

        modelMap.addAttribute("booking", new Booking());
        User user = userService.getUserByUsername(authenticationUser.getUsername());

        boolean isAuthorizedUserAdmin = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUserAdmin);
        modelMap.addAttribute("isUserLogged", true);
//        modelMap.addAttribute("isAuthorizedUserAdmin", true);

        modelMap.addAttribute("currentBookings", bookingService.getCurrentBookingsByUser(user));

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
        // TESTING
//        User user = userService.getUserByUsername(authenticationUser.getUsername());
//        modelMap.addAttribute("currentBookings", bookingService.getCurrentBookingsByUser(user));

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
        User user = userService.getUserByUsername(authenticationUser.getUsername());
        // TESTING
//        modelMap.addAttribute("currentBookings", bookingService.getCurrentBookingsByUser(user));

        Booking newOne = bookingService.createNewBooking(booking, user);
        modelMap.addAttribute("newOne", newOne);
        return "redirect:/booking/" + booking.getId();
    }

    // GET EDIT BOOKING
    @GetMapping(value = "/booking/update/{id}")
    public String showBookingToUpdate(ModelMap modelMap, @PathVariable Long id, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {

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

    // POST EDIT BOOKING

    @PostMapping("/booking/update/{id}")
    public String updateBookingById(@PathVariable(name = "id") Long id, ModelMap modelMap, @Valid @ModelAttribute("booking") Booking booking, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {

        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUser = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUser);
        }
        booking.setId(id);
        bookingService.updateBooking(booking);
        return "redirect:/booking/" + booking.getId();
    }

    @RequestMapping(value = "/delete_booking/{id}", method = RequestMethod.GET)
    public String deleteBookingById(@PathVariable(name = "id") Long id, ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {

        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUser = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUser);
        }
        bookingService.deleteBookingById(id);
        return "redirect:/booking";
    }


//    @GetMapping("/bookings")
//    public String showUserBookings(@Valid @ModelAttribute("booking") Booking booking, ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
//        modelMap.addAttribute("isUserLogged", true);
//        boolean isAuthorizedUser = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN") || grantedAuthority.getAuthority().equals("ROLE_MANAGER"));
//        modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUser);
//
//        User user = userService.getUserByUsername(authenticationUser.getUsername());
//        modelMap.addAttribute("currentBookings", bookingService.getCurrentBookingsByUser(user));
//
//        return "bookings";
//    }


    //    //SEARCH
//    @RequestMapping(value = "hotels", method = RequestMethod.GET)
//    public String findHotelByLocationCity(@RequestParam (value = "search", required = false) String locationCity, Model model) {
//        model.addAttribute("search", hotelService.findHotelByLocationCity(locationCity));
//        return "hotels";
//    }


}

