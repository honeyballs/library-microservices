package com.example.bookservice.service;

import com.example.bookservice.model.Book;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Publishes books after they were created to provide other services with replicas.
 * In a real microservice environment this should be handled asynchronously.
 */
@Service
public class PublishingService {

    private RestTemplate restTemplate = new RestTemplate();
    private final String URL = "http://lend-service:8082/replication/book";

    public void publish(Book book) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Book> httpEntity = new HttpEntity<>(book, headers);
        // The String class is provided as the expected answer type of the recipient.
        restTemplate.postForObject(URL, httpEntity, String.class);
    }


}
