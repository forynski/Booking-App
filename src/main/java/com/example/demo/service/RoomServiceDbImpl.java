package com.example.demo.service;

import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("prototype")
@Slf4j
public class RoomServiceDbImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceDbImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room createNewRoom(Room room) {
        log.info("Creating new hotel");
        return roomRepository.save(room);
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public List<Room> getAllRooms(Integer page, Integer size) {
        log.info("All rooms found");
        return roomRepository.findAll();
    }

    @Override
    public Room updateRoomById(Long id, Room room) {
        if (roomRepository.existsById(id)) {
            room.setId(id);
        }
        return roomRepository.save(room);
    }

    @Override
    public boolean removeRoomById(Long id) {
        log.info("Room successfully deleted from database");
        roomRepository.deleteById(id);
        return true;
    }

    //SEARCH
//    @Override
//    public List<Hotel> findByKeyword(String keyword) {
//        return hotelRepository.findByKeyword(keyword);
//    }

//    @Override
//    public Iterable<Room> findRoomByType(String roomType) {
//        return roomRepository.findRoomByType(roomType);
//    }

}
