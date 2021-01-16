package com.example.demo.service;

import com.example.demo.model.Room;

import java.util.List;

public interface RoomService {

    Room createNewRoom(Room room);

    Room getRoomById(Long id);

    List<Room> getAllRooms(Integer page, Integer size);

    Room updateRoomById(Long id, Room room);

    boolean removeRoomById(Long id);

//    //SEARCH
//    Iterable<Room> findRoomByType(String roomType);


}
