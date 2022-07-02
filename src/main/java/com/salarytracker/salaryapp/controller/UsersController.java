package com.salarytracker.salaryapp.controller;

import com.salarytracker.salaryapp.controller.model.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class UsersController {
    @GetMapping("/users")
    public UserResponse getUsersList() {
        return new UserResponse(Collections.emptyList());
    }
}
