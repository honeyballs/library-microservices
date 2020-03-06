package com.example.lendservice.controller;

import com.example.lendservice.model.Lend;
import com.example.lendservice.model.replication.Book;
import com.example.lendservice.service.LendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class LendController {

    private final String PATH = "/lends";
    private LendService lendService;

    public LendController(LendService lendService) {
        this.lendService = lendService;
    }

    @GetMapping(PATH + "/customer/{customerId}")
    public ResponseEntity<Set<Lend>> getLendsOfCustomer(@PathVariable("customerId") String customerId) {
        return ResponseEntity.ok(lendService.getLendsOfCustomer(customerId));
    }

    @GetMapping(PATH + "/book/{bookId}")
    public ResponseEntity<Set<Lend>> getLendsOfBook(@PathVariable("bookId") String bookId) {
        return ResponseEntity.ok(lendService.getLendsOfBook(bookId));
    }

    @PostMapping(PATH + "/lend/{customerId}")
    public ResponseEntity<Lend> lendBooks(@PathVariable("customerId") String customerId, @RequestBody Set<String> bookIds) {
        return ResponseEntity.ok(lendService.lendBook(customerId, bookIds));
    }

    @GetMapping(PATH + "/return/{lendId}")
    public ResponseEntity<Lend> returnBook(@PathVariable("lendId") String lendId) {
        return ResponseEntity.ok(lendService.returnBooksOfLend(lendId));
    }
}
