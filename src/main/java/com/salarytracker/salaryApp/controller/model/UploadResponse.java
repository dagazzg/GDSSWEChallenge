package com.salarytracker.salaryApp.controller.model;

public class UploadResponse {
    int success;

    public UploadResponse(int success) {
        this.success = success;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
