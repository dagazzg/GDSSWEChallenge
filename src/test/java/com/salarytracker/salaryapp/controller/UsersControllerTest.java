package com.salarytracker.salaryapp.controller;

import com.salarytracker.salaryapp.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class UsersControllerTest {

    @Mock
    UserService userService;

    @Test
    void getUserTest() {

    }

    @Test
    @DisplayName("Should return error when content type header is wrong")
    void wrongContentTypeHeaderTest() {

    }
}
