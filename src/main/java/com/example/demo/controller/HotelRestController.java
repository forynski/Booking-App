package com.example.demo.controller;

import com.example.demo.model.Hotel;
import com.example.demo.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(path = "/booking/hotel")
@Slf4j
public class HotelRestController {

    private final HotelService hotelService;

    public HotelRestController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<?> createNewHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createNewHotel(hotel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHotelById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        if (Objects.nonNull(hotel)) {
            return ResponseEntity.ok(hotel);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping()
    public ResponseEntity<?> getHotels(@RequestParam(required = false) Integer page,
                                       @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(hotelService.getAllHotels(page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHotelById(@PathVariable Long id, @RequestBody Hotel hotel) {
        Hotel updatedHotel = hotelService.updateHotelById(id, hotel);
        if (Objects.nonNull(updatedHotel)) {
            return ResponseEntity.ok(hotel);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeHotelById(@PathVariable Long id) {
        if (hotelService.removeHotelById(id)) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
