package com.salarytracker.salaryapp.controller;

import com.salarytracker.salaryapp.controller.model.UploadResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UploadController {
    @PostMapping("/upload")
    public UploadResponse postUploadUserData() {
        return new UploadResponse(1);
    }
}
