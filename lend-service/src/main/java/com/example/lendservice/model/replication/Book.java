package com.example.lendservice.model.replication;

import com.fasterxml.jackson.annotation.*;

/**
 * The replicated book class.
 * It is immutable, this service is not allowed to change fields.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    private final String id;
    private final String title;
    private final String isbn;
    private final int stock;

    // The annotations are necessary to deserialize immutable objects
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Book(@JsonProperty("id") String id, @JsonProperty("title") String title, @JsonProperty("isbn") String isbn, @JsonProperty("stock") int stock) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getStock() {
        return stock;
    }
}
