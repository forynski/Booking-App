//package com.example.demo.repository;
//
//import com.example.demo.model.Room;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//
//@Repository
//public interface RoomRepository extends JpaRepository<Room, Long> {


//    @Query("SELECT hotelName FROM hotel WHERE hotel.locationCity LIKE :search")
//    @Query(value = "select * from hotel h where h.locationCity like %:keyword% or h.hotelName like %:keyword%", nativeQuery = true)
//    List<Hotel> findByKeyword(@Param("keyword") String keyword);

    //SEARCH
//    @Query(value = "select * from hotel where location_city like %:keyword%", nativeQuery = true)
//    List<Hotel> findByKeyword(@Param("keyword") String keyword);

    //SEARCH
//    Iterable<Room> findRoomByType(String roomType);

    

//}
