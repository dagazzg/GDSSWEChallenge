package com.salarytracker.salaryApp.controller;

import com.salarytracker.salaryApp.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class UsersControllerTest {

    @Mock
    UserService userService;

    @Test
    public void getUserTest() {

    }

    @Test
    @DisplayName("Should return error when content type header is wrong")
    public void wrongContentTypeHeaderTest() {

    }
}
