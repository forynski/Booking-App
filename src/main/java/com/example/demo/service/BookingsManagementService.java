package com.example.demo.service;

import com.example.demo.model.BookingsManagement;

public interface BookingsManagementService {

    BookingsManagement getCurrentBookingsManagement(Integer page, Integer size);
}
