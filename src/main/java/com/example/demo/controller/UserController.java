package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.User;

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
//        // TODO: dodać widok
    }

    @GetMapping("user/add")
    public String showUserAdd(ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
        modelMap.addAttribute("user", new User());

        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUser = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUser);
        }
        return "user-add";
    }

    // TODO: ERROR MAPPING EXAMPLE
    @PostMapping("user/add")
    public String addUser(@Valid @ModelAttribute("user") User user, final Errors errors, ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
        if (errors.hasErrors()) {
            return "user-add";
        }
        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUser = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUser);
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
            boolean isAuthorizedUser = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUser);
        }
        modelMap.addAttribute("user", userService.getUserById(id));
        return "user-update";
    }

    // POST EDIT USER
    @PostMapping(value = "user/update/{id}")
    public String updateUserById(@PathVariable(name = "id") Long id, final Errors errors, ModelMap modelMap, @Valid @ModelAttribute("user") User user, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
        if (errors.hasErrors()) {
            return "one-user";
        }
        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUser = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUser);
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
            boolean isAuthorizedUser = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUser);
        }
        userService.deleteUserById(id);
        return "redirect:/user";
    }
}
