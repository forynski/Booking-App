package com.example.demo.controller;


import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/customer")
    public String customer(ModelMap modelMap) {
        List<Customer> customerPage = customerService.getAllCustomers(1, 100);
        modelMap.addAttribute("customerList", customerService.getAllCustomers(1, 100));
        modelMap.addAttribute("customerPage", customerPage);
//        return "customer"; TODO: create model views
        return null;
    }

    @GetMapping(path = "/customer/add")
    public String showCustomerAdd(ModelMap modelMap) {
        modelMap.addAttribute("customer", new Customer());
//        return "customer-add";
        return null;
    }

    @PostMapping(path = "/customer/add")
    public String addCustomer(@Valid @ModelAttribute("customer") Customer customer) {
        customerService.createNewCustomer(customer);
        return "redirect:/";
    }

}
