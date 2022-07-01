package com.salarytracker.salaryApp.controller.model;

import java.util.List;

public class UserResponse {
    List<User> results;

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
