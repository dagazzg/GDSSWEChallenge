package com.salarytracker.salaryapp.controller;

import com.salarytracker.salaryapp.controller.model.UserRequestParams;
import com.salarytracker.salaryapp.controller.model.UserResponse;
import com.salarytracker.salaryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> getUsersList(@Valid UserRequestParams userRequestParams) {
        // TODO: improvement: write a custom validator and handle it as a annotation in the POJO
        if (userRequestParams.getMax() < userRequestParams.getMin()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Max cannot be smaller than Min");
        }

        return new ResponseEntity<>(userService.getUsersBasedOnFilters(userRequestParams), HttpStatus.OK);
    }
}
