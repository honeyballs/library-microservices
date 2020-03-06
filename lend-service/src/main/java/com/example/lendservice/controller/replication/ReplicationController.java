package com.example.lendservice.controller.replication;

import com.example.lendservice.model.replication.Book;
import com.example.lendservice.model.replication.Customer;
import com.example.lendservice.service.replication.BookReplicationService;
import com.example.lendservice.service.replication.CustomerReplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles replication requests
 */
@RestController
public class ReplicationController {

    private final String PATH = "/replication";
    private BookReplicationService bookService;
    private CustomerReplicationService customerService;

    public ReplicationController(BookReplicationService bookService, CustomerReplicationService customerService) {
        this.bookService = bookService;
        this.customerService = customerService;
    }

    @PostMapping(PATH + "/book")
    public ResponseEntity<String> replicateBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.persistReplica(book));
    }

    @PostMapping(PATH + "/customer")
    public ResponseEntity<String> replicateCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.persistReplica(customer));
    }

}
