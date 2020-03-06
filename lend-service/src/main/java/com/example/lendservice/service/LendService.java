package com.example.lendservice.service;

import com.example.lendservice.model.Lend;
import com.example.lendservice.model.replication.Book;
import com.example.lendservice.service.replication.BookReplicationService;
import com.example.lendservice.service.replication.CustomerReplicationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LendService {

    private Set<Lend> lends = new HashSet<>();

    // We do not validate if enough copies are available
    public Lend lendBook(String customerId, Set<String> bookIds) {
        Lend lend = new Lend(UUID.randomUUID().toString(), LocalDate.now().plusMonths(1), customerId, bookIds);
        lends.add(lend);
        return lend;
    }

    public Set<Lend> getLendsOfCustomer(String customerId) {
        return lends.stream().filter(lend -> lend.getCustomerId().equals(customerId)).collect(Collectors.toSet());
    }

    public Set<Lend> getLendsOfBook(String bookId) {
        return lends.stream().filter(lend -> lend.getBookIds().contains(bookId)).collect(Collectors.toSet());
    }

    public Lend returnBooksOfLend(String lendId) {
        Lend returned = lends.stream().filter(lend -> lend.getId().equals(lendId)).findFirst().orElseThrow();
        returned.setReturned(true);
        return returned;
    }


}
