package com.rest.Integration.sample;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionResponse {
    private Integer page;
    private Integer per_page;
    private Integer total;
    private List<Transaction> data;

    public List<Transaction> getData() {
        return data;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    private Integer total_pages;
}
