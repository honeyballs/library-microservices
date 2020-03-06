package com.example.lendservice.controller;

import com.example.lendservice.model.replication.Book;
import com.example.lendservice.service.replication.BookReplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Converts to and from JSON automatically using the Jackson library.
 */
@RestController
public class BookController {

    private final String PATH = "/books";
    private BookReplicationService bookService;

    public BookController(BookReplicationService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(PATH)
    public ResponseEntity<Set<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAll().stream().map(book -> bookService.setAvailableStock(book)).collect(Collectors.toSet()));
    }

    @GetMapping(PATH + "/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") String id) {
        return ResponseEntity.ok(bookService.setAvailableStock(bookService.getBookById(id)));
    }

}
