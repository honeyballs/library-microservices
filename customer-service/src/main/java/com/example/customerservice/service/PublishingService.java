package com.example.customerservice.service;

import com.example.customerservice.model.Customer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Publishes customers after they were created to provide other services with replicas.
 * In a real microservice environment this should be handled asynchronously.
 */
@Service
public class PublishingService {

    private RestTemplate restTemplate = new RestTemplate();
    private final String URL = "http://lend-service:8082/replication/customer";

    public void publish(Customer customer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Customer> httpEntity = new HttpEntity<>(customer, headers);
        // The String class is provided as the expected answer type of the recipient.
        restTemplate.postForObject(URL, httpEntity, String.class);
    }


}
