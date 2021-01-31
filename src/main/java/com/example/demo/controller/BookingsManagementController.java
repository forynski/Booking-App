package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.BookingsManagement;
import com.example.demo.service.BookingService;
import com.example.demo.service.BookingsManagementService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class BookingsManagementController {

    private final BookingsManagementService bookingsManagementService;
    private final BookingService bookingService;


    public BookingsManagementController(BookingsManagementService bookingsManagementService, BookingService bookingService) {
        this.bookingsManagementService = bookingsManagementService;
        this.bookingService = bookingService;
    }


    @GetMapping("/booking/current-bookings")
    public String getAllCurrentBookings(ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser,
                                        @RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "5") Integer size){
        modelMap.addAttribute("isUserLogged", true);
        boolean isAuthorizedUserAdminOrManager = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUserAdminOrManager);
        BookingsManagement currentBookingsManagement = bookingsManagementService.getCurrentBookingsManagement(page - 1, size);
        modelMap.addAttribute("currentBookingsManagement", currentBookingsManagement.getBookingsManagementContentList());
        Page<Booking> currentBookingsPage = currentBookingsManagement.getBookingPage();
        modelMap.addAttribute("currentBookingsPage", currentBookingsPage);

        int totalPages = currentBookingsPage.getTotalPages();
        getPageNumbers(modelMap, page, totalPages);

        return "booking-current-bookings";
    }

    private void getPageNumbers(ModelMap modelMap, Integer page, int totalPages) {
        if (totalPages > 0) {
            int pageOffset = 2;
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .filter(integer -> (integer == 1) || ((integer >= page - pageOffset) && (integer <= page + pageOffset)) || (integer == totalPages))
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
    }
}
