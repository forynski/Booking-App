package com.example.demo.service;

import com.example.demo.model.Hotel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HotelService {

    Hotel createNewHotel(Hotel hotel);

    Hotel getHotelById(Long id);

    List<Hotel> getAllHotels(Integer page, Integer size);

    Hotel updateHotelById(Long id, Hotel hotel);

    boolean removeHotelById(Long id);

    //SEARCH
//    List<Hotel> findByKeyword(String keyword);

    Iterable<Hotel> findHotelByLocationCity(String locationCity);

}
