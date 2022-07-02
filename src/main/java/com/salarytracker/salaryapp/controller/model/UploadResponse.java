package com.salarytracker.salaryapp.controller.model;

public class UploadResponse {
    private int success;

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
