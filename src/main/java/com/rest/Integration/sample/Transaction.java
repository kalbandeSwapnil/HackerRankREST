package com.rest.Integration.sample;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
    private Location location;
    private String ip;

    public Location getLocation() {
        return location;
    }

    public String getIp() {
        return ip;
    }
}



