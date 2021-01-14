package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.Hotel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@Slf4j
public class HomepageController {

    @GetMapping("/")
    public String homePage(ModelMap modelMap, Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        model.addAttribute("booking", new Booking());
        if (isUserLogged) {
            boolean isAuthorizedUserAdmin = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUserAdmin);
        }
        return "homepage";
    }

//    @GetMapping("/")
//    public String main(Model model) {
//        model.addAttribute("booking", new Booking());
//        return "homepage";
//    }

    @PostMapping("/")
    public String save(Booking booking, Model model) {
        model.addAttribute("booking", booking);
        return "test";
    }


}
