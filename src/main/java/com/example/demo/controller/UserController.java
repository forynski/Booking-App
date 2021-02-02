package com.example.demo.controller;

import com.example.demo.model.User;

import com.example.demo.service.BookingService;
import com.example.demo.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
public class UserController {

    private final UserService userService;
    private final BookingService bookingService;

    public UserController(UserService userService, BookingService bookingService) {
        this.userService = userService;
        this.bookingService = bookingService;
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
//        modelMap.addAttribute("isAuthorizedUserAdmin", true);

        return "user";
    }

    @GetMapping("user/{id}")
    public String user(ModelMap modelMap, @PathVariable Long id, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
        modelMap.addAttribute("user", userService.getUserById(id));
        modelMap.addAttribute("updateUser", new User());

        modelMap.addAttribute("isUserLogged", true);
        boolean isAuthorizedUserAdmin = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUserAdmin);
        modelMap.addAttribute("currentBookings", bookingService.getCurrentBookingsByUser(userService.getUserById(id)));
        return "one-user";
    }

    @GetMapping("user/add")
    public String showUserAdd(ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
        modelMap.addAttribute("user", new User());

        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUserAdmin = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUserAdmin);
        }
        return "user-add";
    }


    @PostMapping("user/add")
    public String addUser(@Valid @ModelAttribute("user") User user, final Errors errors, ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
        if (errors.hasErrors()) {
            return "user-add";
        }
        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUserAdmin = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUserAdmin);
        }
        User newOne = userService.createNewUser(user);
        modelMap.addAttribute("newOne", newOne);
        return "redirect:/user/" + user.getId();
    }

    // GET EDIT USER
    @GetMapping(value = "/user/update/{id}")
    public String showUserToUpdate(ModelMap modelMap, @PathVariable Long id, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {

        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUserAdmin = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ROLE"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUserAdmin);
        }
        modelMap.addAttribute("user", userService.getUserById(id));
        return "user-update";

    }

    // POST EDIT USER
    @PostMapping(value = "user/update/{id}")
    public String updateUserById(@PathVariable(name = "id") Long id, ModelMap modelMap, @Valid @ModelAttribute("user") User user, final Errors errors, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
        if (errors.hasErrors()) {
            return "one-user";
        }
        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUserAdmin = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUserAdmin);
        }

        user.setId(id);
        userService.updateUser(user);
        return "redirect:/user/" + user.getId();

    }

    @RequestMapping(value = "/delete_user/{id}", method = RequestMethod.GET)
    public String deleteUserById(@PathVariable(name = "id") Long id, ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {

        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUserAdmin = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUserAdmin);
        }
        userService.deleteUserById(id);
        return "redirect:/user";
    }

    // TODO: CHECK IF WE NEED BELOW MAPPING
    @GetMapping("/user/profile/")
    public String getUserProfile(@ModelAttribute(value = "user") ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser){

        modelMap.addAttribute("isUserLogged", true);
        boolean isAuthorizedUserAdmin = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUserAdmin);

        User user = new User();
        if (Objects.nonNull(userService.getUserByUsername(authenticationUser.getUsername()))){
            user = userService.getUserByUsername(authenticationUser.getUsername());
        }
        modelMap.addAttribute("user",user);
        modelMap.addAttribute("currentBookings", bookingService.getCurrentBookingsByUser(user));

        return "redirect:/user-profile";
    }



}
