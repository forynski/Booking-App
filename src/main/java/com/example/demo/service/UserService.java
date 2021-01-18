package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.temp.CurrentUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findUserByEmail(String email);

    void saveUser(CurrentUser currentUser);

    Long getLoggedUserId();

    User createNewUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

}
