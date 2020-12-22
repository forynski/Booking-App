package com.example.demo.service;

import com.example.demo.model.Hotel;
import com.example.demo.repository.HotelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("prototype")
@Slf4j
public class HotelServiceDbImpl implements HotelService {

    private final HotelRepository hotelRepository;

    public HotelServiceDbImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel createNewHotel(Hotel hotel) {
        log.info("Creating new hotel");
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).orElse(null);
    }

    @Override
    public List<Hotel> getAllHotels(Integer page, Integer size) {
        log.info("All hotels found");
        return hotelRepository.findAll();
    }

    @Override
    public Hotel updateHotelById(Long id, Hotel hotel) {
        if (hotelRepository.existsById(id)) {
            hotel.setId(id);
        }
        return hotelRepository.save(hotel);
    }

    @Override
    public boolean removeHotelById(Long id) {
        log.info("Customer successfully deleted from database");
        hotelRepository.deleteById(id);
        return true;
    }
}
