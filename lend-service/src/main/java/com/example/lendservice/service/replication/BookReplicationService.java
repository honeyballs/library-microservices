package com.example.lendservice.service.replication;

import com.example.lendservice.model.Lend;
import com.example.lendservice.model.replication.Book;
import com.example.lendservice.service.LendService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BookReplicationService {

    private Set<Book> books = new HashSet<>();
    private LendService lendService;

    public BookReplicationService(LendService lendService) {
        this.lendService = lendService;
    }

    public String persistReplica(Book book) {
        books.add(book);
        return "Book " + book.getTitle() + " replicated successfully";
    }

    public Set<Book> getAll() {
        return books;
    }

    public Book getBookById(String id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst().orElseThrow();
    }

    /**
     * This method calculates how many copies of a book are available.
     * @param book
     * @return Returns a new Book instance with the stock set to the available amount
     */
    public Book setAvailableStock(Book book) {
        int amountLent = (int) lendService.getLendsOfBook(book.getId()).stream().filter(lend -> !lend.isReturned()).count();
        return new Book(book.getId(), book.getTitle(), book.getIsbn(), book.getStock() - amountLent);
    }

}
