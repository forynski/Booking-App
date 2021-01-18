package com.example.demo.repository;

import com.example.demo.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Booking findById(Integer bookingId);

    Collection<Booking> findAllByUserId(Long userId);
}
