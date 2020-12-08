package com.example.demo.service;

import com.example.demo.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer createNewCustomer(Customer customer);

    Customer getCustomerById(Long id);

    List<Customer> getAllCustomers(Integer page, Integer size);

    Customer updateCustomerById(Long id, Customer customer);

    boolean removeCustomerById(Long id);

}
