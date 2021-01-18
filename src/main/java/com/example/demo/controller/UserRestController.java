package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController

//TODO: sprawdzić RESTa

@RequestMapping(path = "api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<?> registerNewUser(@RequestBody User user, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
        User createdUser = userService.createNewUser(user);
        if (Objects.isNull(createdUser)) {
            return ResponseEntity.badRequest().build();
        }
        log.info("USERNAME: " +authenticationUser.getUsername());
        log.info("USER: " +authenticationUser.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}
