package com.example.customerservice.service;

import com.example.customerservice.model.Customer;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class CustomerService {

    // Is injected by the application context via the constructor
    private PublishingService publishingService;
    private Set<Customer> customers = new HashSet<>();

    public CustomerService(PublishingService publishingService) {
        this.publishingService = publishingService;
    }

    /**
     * Simulates persisting and publishing a new customer.
     *
     * @param customer
     */
    public Customer persistCustomer(Customer customer) {
        customer.setId(UUID.randomUUID().toString());
        customers.add(customer);
        publishingService.publish(customer);
        return customer;
    }

    public Set<Customer> getAll() {
        return customers;
    }

    public Customer getCustomerById(String id) {
        return customers.stream().filter(customer -> customer.getId().equals(id)).findFirst().orElseThrow();
    }

}
