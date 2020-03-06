package com.example.customerservice.controller;

import com.example.customerservice.model.Customer;
import com.example.customerservice.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class CustomerController {

    private final String PATH = "/customers";
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(PATH)
    public ResponseEntity<Set<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping(PATH + "/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") String id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping(PATH)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.persistCustomer(customer));
    }

}
