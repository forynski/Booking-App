package com.example.demo.service;

import com.example.demo.model.Booking;
import com.example.demo.model.BookingsManagement;
import com.example.demo.model.BookingsManagementContent;
import com.example.demo.model.User;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class BookingsManagementServiceDbImpl implements BookingsManagementService {

    public final BookingService bookingService;
    private final UserService userService;

    public BookingsManagementServiceDbImpl(BookingService bookingService, UserService userService) {
        this.bookingService = bookingService;
        this.userService = userService;
    }

    @Override
    public BookingsManagement getCurrentBookingsManagement(Integer page, Integer size) {
        List<BookingsManagementContent> bookingsManagementContentList = new ArrayList<>();

        Page<Booking> currentReservations = bookingService.getCurrentBookings(page, size);
        return getBookingsManagement(bookingsManagementContentList, currentReservations);
    }

    private BookingsManagement getBookingsManagement(List<BookingsManagementContent> bookingsManagementContentList, Page<Booking> bookingPage) {
        List<Booking> bookingList = bookingPage.getContent();

        for (Booking booking : bookingList) {
            User user = userService.getUserByBooking(booking);
            bookingsManagementContentList.add(BookingsManagementContent.builder()
                    .booking(booking).user(user).build());
        }

        return BookingsManagement.builder()
                .bookingPage(bookingPage)
                .bookingsManagementContentList(bookingsManagementContentList)
                .build();
    }
}
