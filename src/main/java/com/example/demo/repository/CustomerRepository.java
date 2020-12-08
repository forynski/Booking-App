package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByPublished(boolean published);

    List<Customer> findByTitleContaining(String title);
}
