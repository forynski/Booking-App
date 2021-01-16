package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.Room;
import com.example.demo.service.RoomService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping(path = "/room")
    public String room(ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
        List<Room> roomPage = roomService.getAllRooms(1, 100);
        modelMap.addAttribute("roomList", roomService.getAllRooms(1, 100));
        modelMap.addAttribute("roomPage", roomPage);

        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUser = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUser);
        }
        return "room";
    }

    @GetMapping(path = "/room/add")
    public String showRoomAdd(ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {
        modelMap.addAttribute("room", new Room());

        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUser = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUser);
        }
        return "room-add";
    }

    @PostMapping(path = "/room/add")
    public String addRoom(@Valid @ModelAttribute("room") Room room, ModelMap modelMap, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticationUser) {

        boolean isUserLogged = Objects.nonNull(authenticationUser);
        modelMap.addAttribute("isUserLogged", isUserLogged);
        if (isUserLogged) {
            boolean isAuthorizedUser = authenticationUser.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            modelMap.addAttribute("isAuthorizedUserAdmin", isAuthorizedUser);
        }
        roomService.createNewRoom(room);
        return "redirect:/room";
    }

//    //SEARCH
//    @RequestMapping(value = "hotels", method = RequestMethod.GET)
//    public String findHotelByLocationCity(@RequestParam (value = "search", required = false) String locationCity, Model model) {
//        model.addAttribute("search", hotelService.findHotelByLocationCity(locationCity));
//        return "hotels";
//    }

}
