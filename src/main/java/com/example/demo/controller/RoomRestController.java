//package com.example.demo.controller;
//
//import com.example.demo.model.Room;
//import com.example.demo.service.RoomService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Objects;
//
//@RestController
//@RequestMapping(path = "/booking/room")
//@Slf4j
//public class RoomRestController {
//
//    private final RoomService roomService;
//
//    public RoomRestController(RoomService roomService) {
//        this.roomService = roomService;
//    }
//
//    @PostMapping
//    public ResponseEntity<?> createNewRoom(@RequestBody Room room) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.createNewRoom(room));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getRoomById(@PathVariable Long id) {
//        Room room = roomService.getRoomById(id);
//        if (Objects.nonNull(room)) {
//            return ResponseEntity.ok(room);
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @GetMapping()
//    public ResponseEntity<?> getRooms(@RequestParam(required = false) Integer page,
//                                      @RequestParam(required = false) Integer size) {
//        return ResponseEntity.ok(roomService.getAllRooms(page, size));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateRoomById(@PathVariable Long id, @RequestBody Room room) {
//        Room updatedRoom = roomService.updateRoomById(id, room);
//        if (Objects.nonNull(updatedRoom)) {
//            return ResponseEntity.ok(room);
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> removeRoomById(@PathVariable Long id) {
//        if (roomService.removeRoomById(id)) {
//            return ResponseEntity.accepted().build();
//        }
//        return ResponseEntity.badRequest().build();
//    }
//
////    //SEARCH:
////    @GetMapping("/hotel/{locationCity}")
////    public ResponseEntity<?> getHotelsByCity(@PathVariable String locationCity) {
////        List<Hotel> hotels =hotelService.findHotelByLocationCity(locationCity);
////        return ResponseEntity.notFound().build();
////    }
//
//}
