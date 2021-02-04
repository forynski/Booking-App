package com.example.demo.service;

import com.example.demo.model.Booking;
import com.example.demo.model.User;


import java.util.List;

public interface UserService {

    User createNewUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    Boolean deleteUserById(Long id);

    User updateUser(User user);

    User getUserByUsername(String username);

    User getUserByBooking(Booking booking);


}
