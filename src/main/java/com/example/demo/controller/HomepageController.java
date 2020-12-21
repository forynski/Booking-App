package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomepageController {

    @GetMapping("/")
    public String homepage(ModelMap modelMap) {
        return "homepage";
    }
}
