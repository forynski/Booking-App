package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.Hotel;
import com.example.demo.service.HotelService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping(path = "/hotel")
    public String hotel(ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
        List<Hotel> hotelPage = hotelService.getAllHotels(1, 100);
        modelMap.addAttribute("hotelList", hotelService.getAllHotels(1, 100));
        modelMap.addAttribute("hotelPage", hotelPage);

        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUser = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUser);
        }
        return "hotel";
    }

    @GetMapping(path = "/hotel/add")
    public String showHotelAdd(ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
        modelMap.addAttribute("hotel", new Hotel());

        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUser = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUser);
        }
        return "hotel-add";
    }

    @PostMapping(path = "/hotel/add")
    public String addHotel(@Valid @ModelAttribute("hotel") Hotel hotel, ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {

        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUser = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUser);
        }
        hotelService.createNewHotel(hotel);
        return "redirect:/hotel";
    }

//    //SEARCH
//    @RequestMapping(value = "hotels", method = RequestMethod.GET)
//    public String findHotelByLocationCity(@RequestParam (value = "search", required = false) String locationCity, Model model) {
//        model.addAttribute("search", hotelService.findHotelByLocationCity(locationCity));
//        return "hotels";
//    }

    //SEARCH
    @RequestMapping(value = "hotels", method = RequestMethod.GET)
    public String findHotelByLocationCity(@RequestParam (value = "search", required = false) String locationCity, ModelMap modelMap, Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
        boolean isUserLogged = Objects.nonNull(authenticationUser);
        model.addAttribute("search", hotelService.findHotelByLocationCity(locationCity));
        modelMap.addAttribute("isUserLogged", isUserLogged);
        model.addAttribute("booking", new Booking());
        if (isUserLogged) {
            boolean isAuthorizedUserAdmin = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUserAdmin);
        }
        return "hotels";
    }

}
