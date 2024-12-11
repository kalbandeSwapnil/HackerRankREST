package com.rest.Integration.sample;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    private String id;

    public String getId() {
        return id;
    }
}
