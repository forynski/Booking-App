package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUsers(ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser,
                           @RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "2") Integer size) {
        List<User> userPage = userService.getAllUsers();
        modelMap.addAttribute("userList", userService.getAllUsers());
        modelMap.addAttribute("userPage", userPage);

        boolean isAuthorizedUserAdmin = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUserAdmin);
        modelMap.addAttribute("isUserLogged", true);
        modelMap.addAttribute("isAuthorizedUserAdmin", true);
        return "user";
    }

    @GetMapping("user/{id}")
    public String user(ModelMap modelMap, @PathVariable Long id, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
        modelMap.addAttribute("user", userService.getUserById(id));
        modelMap.addAttribute("updateUser", new User());

        modelMap.addAttribute("isUserLogged", true);
        modelMap.addAttribute("isAuthorizedUser", true);
        boolean isAuthorizedUserAdmin = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUserAdmin);
        return "one-user";
        // TODO: dodać widok
    }

    @PostMapping("user/add")
    public String addUser(@Valid @ModelAttribute("user") User user, final Errors errors) {
        if (errors.hasErrors()) {
            return "user-add";
        }
        userService.createNewUser(user);
        return "redirect:/";
    }
}
