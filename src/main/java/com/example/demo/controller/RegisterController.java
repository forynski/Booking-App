package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@Slf4j
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showAddUser(ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUserAdmin = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUserAdmin);
        }
        modelMap.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@Valid @ModelAttribute("user") User user, final Errors errors, ModelMap modelMap) {
        modelMap.addAttribute("isUserLogged", false);
        modelMap.addAttribute("isAuthorizedUserAdmin", false);
        if (errors.hasErrors()) {
            return "register";
        }
        userService.createNewUser(user);
//        User createdUser = userService.createNewUser(user);
//        if (Objects.isNull(createdUser)) {
//            modelMap.addAttribute("userExistsError", "Unable to create user, because that username or email already exist.");
//            return "register";
//        }
//
        boolean matchPasswords = user.getPassword().equals(user.getMatchingPassword());
        if (!matchPasswords) {
            modelMap.addAttribute("userExistsError", "Unable to create user, because that username or email already exist.");
            return "register";
        }
        return "redirect:/login";
    }

}
