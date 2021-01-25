package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

//public interface UserService extends UserDetailsService {
public interface UserService {

    // DAO

    User createNewUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    Boolean deleteUserById(Long id);

    User updateUser(User user);


}
