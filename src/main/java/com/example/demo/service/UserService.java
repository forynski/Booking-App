package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    // DAO

    User createNewUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

}
