package com.example.bookservice.service;

import com.example.bookservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class BookService {

    // Is injected by the application context via the constructor
    private PublishingService publishingService;
    private Set<Book> books = new HashSet<>();

    public BookService(PublishingService publishingService) {
        this.publishingService = publishingService;
    }

    /**
     * Simulates persisting and publishing a new book.
     *
     * @param book
     */
    public Book persistBook(Book book) {
        book.setId(UUID.randomUUID().toString());
        books.add(book);
        publishingService.publish(book);
        return book;
    }

    public Set<Book> getAll() {
        return books;
    }

    public Book getBookById(String id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst().orElseThrow();
    }

}
