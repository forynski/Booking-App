package com.example.demo.controller;

import com.example.demo.model.Hotel;
import com.example.demo.service.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping(path = "/hotel")
    public String hotel(ModelMap modelMap) {
        List<Hotel> hotelPage = hotelService.getAllHotels(1, 100);
        modelMap.addAttribute("hotelList", hotelService.getAllHotels(1, 100));
        modelMap.addAttribute("hotelPage", hotelPage);
        return null;
    }

    @GetMapping(path = "/hotel/add")
    public String showHotelAdd(ModelMap modelMap) {
        modelMap.addAttribute("hotel", new Hotel());
//        return "hotel-add";
        return null;
    }

    @PostMapping(path = "/hotel/add")
    public String addHotel(@Valid @ModelAttribute("hotel") Hotel hotel) {
        hotelService.createNewHotel(hotel);
        return "redirect:/";
    }

}
