package com.salarytracker.salaryapp.controller;

import com.salarytracker.salaryapp.controller.model.UserRequestParams;
import com.salarytracker.salaryapp.controller.model.UserResponse;
import com.salarytracker.salaryapp.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UsersController {
    UserService userService = new UserService();

    @GetMapping("/users")
    public UserResponse getUsersList(@Valid UserRequestParams userRequestParams) {
        return userService.getUsersBasedOnFilters(userRequestParams);
    }
}
