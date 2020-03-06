package com.example.lendservice.model.replication;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The replicated customer class.
 * It is immutable, this service is not allowed to change fields.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

    private final String id;
    private final String firstname;
    private final String lastname;

    // The annotations are necessary to deserialize immutable objects
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Customer(@JsonProperty("id") String id, @JsonProperty("firstname") String firstname, @JsonProperty("lastname") String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

}
