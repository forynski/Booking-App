package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Controller
@Slf4j
public class HomepageController {

    @GetMapping("/")
    public String homePage(ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUserAdminOrManager = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN") || grantedAuthority.getAuthority().equals("ROLE_MANAGER"));
            modelMap.addAttribute("isAuthorizedUserAdminOrManager", isAuthorizedUserAdminOrManager);
        }
        return "homepage";
    }
}
