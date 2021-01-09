package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUserAdminOrManager = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN") || grantedAuthority.getAuthority().equals("ROLE_MANAGER"));
            modelMap.addAttribute("isAuthorizedUserAdminOrManager", isAuthorizedUserAdminOrManager);
        }
        return "login";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String loginPage(@RequestParam(value = "error", required = false) String error,
//                            @RequestParam(value = "logout", required = false) String logout,
//                            Model model) {
//        String errorMessge = null;
//        if(error != null) {
//            errorMessge = "Username or Password is incorrect !!";
//        }
//        if(logout != null) {
//            errorMessge = "You have been successfully logged out !!";
//        }
//        model.addAttribute("errorMessge", errorMessge);
//        return "login";
//    }

}
