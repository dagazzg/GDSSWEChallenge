package com.salarytracker.salaryapp.controller;

import com.salarytracker.salaryapp.controller.model.UserDTO;
import com.salarytracker.salaryapp.controller.model.UserResponse;
import com.salarytracker.salaryapp.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
@WithMockUser(username = "admin", password = "test1234", roles = "ADMIN")
class UsersControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    UserController userController;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("GET without any query params")
    void getUserTest() {
        when(userService.getUsersBasedOnFilters(any())).thenReturn(mockUserServiceCall());

        webTestClient.get()
                .uri("/users")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @DisplayName("Should return error when min is negative")
    void wrongMinParamTest() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/users")
                        .queryParam("min", -50)
                        .build())
                .exchange().expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("Should return error when max is negative")
    void wrongMaxParamTest() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/users")
                        .queryParam("max", -50)
                        .build())
                .exchange().expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("Should return error when max is smaller than min")
    void maxSmallerThanMinTest() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/users")
                        .queryParam("min", 5000.0)
                        .queryParam("max", 4000.0)
                        .build())
                .exchange().expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("Should return error when limit is negative")
    void wrongLimitParamTest() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/users")
                        .queryParam("limit", -1)
                        .build())
                .exchange().expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("Should return error when offset is negative")
    void wrongOffsetParamTest() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/users")
                        .queryParam("offset", -1)
                        .build())
                .exchange().expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("Should still work when sort value is illegal")
    void wrongSortParamTest() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/users")
                        .queryParam("sort", "invalid")
                        .build())
                .exchange().expectStatus().isOk();
    }

    private UserResponse mockUserServiceCall() {
        List<UserDTO> userList = List.of(new UserDTO("John", 2500.05),
                new UserDTO("Mary Posa", 14000.00));
        return new UserResponse(userList);
    }
}
