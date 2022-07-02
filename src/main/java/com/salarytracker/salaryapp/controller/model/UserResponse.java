package com.salarytracker.salaryapp.controller.model;

import java.util.List;

public class UserResponse {
    private List<User> results;

    public UserResponse() {
    }

    public UserResponse(List<User> results) {
        this.results = results;
    }

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }
}
