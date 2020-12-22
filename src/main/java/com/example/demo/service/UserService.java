package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {

    User createNewUser(User user);

    List<User> getAllUsers(Integer page, Integer size);

    User getUserById(Long id);

    User updateUserById(Long id, User user);

    Boolean deleteUserById(Long id);
}
