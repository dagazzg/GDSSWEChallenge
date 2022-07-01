package com.salarytracker.salaryApp.controller;

import com.salarytracker.salaryApp.controller.model.UploadResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UploadController {
    @PostMapping("/upload")
    public UploadResponse postUploadUserData() {
        return new UploadResponse(1);
    }
}
