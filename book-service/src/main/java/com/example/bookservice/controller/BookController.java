package com.example.bookservice.controller;

import com.example.bookservice.model.Book;
import com.example.bookservice.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * The REST Api of the book microservice.
 * Converts to and from JSON automatically using the Jackson library.
 */
@RestController
public class BookController {

    private final String PATH = "/books";
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(PATH)
    public ResponseEntity<Set<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping(PATH + "/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") String id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping(PATH)
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.persistBook(book));
    }

}
