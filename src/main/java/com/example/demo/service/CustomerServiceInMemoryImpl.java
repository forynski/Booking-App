package com.example.demo.service;

import com.example.demo.model.Customer;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Primary
@Service
@Scope("singleton")
public class CustomerServiceInMemoryImpl implements CustomerService {

    private final Map<Long, Customer> customerMap = new HashMap<>();
    private Long nextId = 1L;


    @Override
    public Customer createNewCustomer(Customer customer) {
        customer.setId(getNextId());
        customerMap.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerMap.getOrDefault(id, null);
    }

    @Override
    public List<Customer> getAllCustomers(Integer page, Integer size) {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer updateCustomerById(Long id, Customer customer) {
        if (customerMap.containsKey(id)) {
            customer.setId(id);
            return customerMap.replace(id, customer);
        }
        return null;
    }

    @Override
    public boolean removeCustomerById(Long id) {
        if (customerMap.containsKey(id)) {
            customerMap.remove(id);
            return true;
        }
        return false;
    }

    private Long getNextId() {
        return nextId++;
    }
}

