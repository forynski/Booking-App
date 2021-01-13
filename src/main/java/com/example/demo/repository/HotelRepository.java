package com.example.demo.repository;

import com.example.demo.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

//    List<Hotel> findHotelByLocationCity(String locationCity);
//    @Query("SELECT hotelName FROM hotel WHERE hotel.locationCity LIKE :search")
//    @Query(value = "select * from hotel h where h.locationCity like %:keyword% or h.hotelName like %:keyword%", nativeQuery = true)
//    List<Hotel> findByKeyword(@Param("keyword") String keyword);

    //SEARCH
//    @Query(value = "select * from hotel where location_city like %:keyword%", nativeQuery = true)
//    List<Hotel> findByKeyword(@Param("keyword") String keyword);

    Iterable<Hotel> findHotelByLocationCity(String locationCity);

}
