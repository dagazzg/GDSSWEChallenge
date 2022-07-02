package com.salarytracker.salaryapp.controller.model;

import java.util.List;

public class UserResponse {
    private List<UserDTO> results;

    public UserResponse() {
    }

    public UserResponse(List<UserDTO> results) {
        this.results = results;
    }

    public List<UserDTO> getResults() {
        return results;
    }

    public void setResults(List<UserDTO> results) {
        this.results = results;
    }
}
