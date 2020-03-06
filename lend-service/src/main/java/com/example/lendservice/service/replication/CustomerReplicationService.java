package com.example.lendservice.service.replication;

import com.example.lendservice.model.replication.Customer;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomerReplicationService {

    private Set<Customer> customers = new HashSet<>();

    public String persistReplica(Customer customer) {
        customers.add(customer);
        return "Customer " + customer.getLastname() + " replicated successfully";
    }

    public Set<Customer> getAll() {
        return customers;
    }

    public Customer getCustomerById(String id) {
        return customers.stream().filter(customer -> customer.getId().equals(id)).findFirst().orElseThrow();
    }
    
}
