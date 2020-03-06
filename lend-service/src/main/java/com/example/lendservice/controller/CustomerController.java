package com.example.lendservice.controller;

import com.example.lendservice.model.replication.Customer;
import com.example.lendservice.service.replication.CustomerReplicationService;
import com.example.lendservice.service.replication.CustomerReplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Converts to and from JSON automatically using the Jackson library.
 */
@RestController
public class CustomerController {

    private final String PATH = "/customers";
    private CustomerReplicationService customerService;

    public CustomerController(CustomerReplicationService customerService) {
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

}
