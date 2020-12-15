package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("prototype")
@Slf4j
public class CustomerServiceDbImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceDbImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createNewCustomer(Customer customer) {
        log.info("Creating new customer");
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Customer> getAllCustomers(Integer page, Integer size) {
        return customerRepository.findAll();
    }


    @Override
    public Customer updateCustomerById(Long id, Customer customer) {
        return null;
    }

    @Override
    public boolean removeCustomerById(Long id) {
        log.info("Customer successfully deleted from database");
        customerRepository.deleteById(id);
        return true;
    }

}

