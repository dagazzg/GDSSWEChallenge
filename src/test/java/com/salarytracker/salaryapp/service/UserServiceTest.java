package com.salarytracker.salaryapp.service;

import com.salarytracker.salaryapp.controller.model.SortCriteria;
import com.salarytracker.salaryapp.controller.model.UserRequestParams;
import com.salarytracker.salaryapp.exception.UserException;
import com.salarytracker.salaryapp.repository.User;
import com.salarytracker.salaryapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
//    cannot use @Mock because @beforeall is run first
    UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeAll
    void setup() {
        userRepository = mock(UserRepository.class);
        given(userRepository.findBySalary(anyDouble(), anyDouble())).willReturn(mockRepositoryCall());
    }

    @Test
    @DisplayName("Should get user based on the salary range")
    void getUserUsingSalaryRange() {
        UserRequestParams params = new UserRequestParams(0.0, 100000.0, 0, 0, null);
        var response = userService.getUsersBasedOnFilters(params);
        assertThat(response.getResults()).hasSize(3);
        assertThat(response.getResults().get(0).getName()).isEqualTo("John");
        assertThat(response.getResults().get(0).getSalary()).isEqualTo(2500.05);
        assertThat(response.getResults().get(2).getName()).isEqualTo("Jane Doe");
        assertThat(response.getResults().get(2).getSalary()).isEqualTo(12345.6);
    }

//    @Test
//    @DisplayName("Should return empty list when no salary is in range ")
//    void noResultsTest() {
//        given(userRepository.findBySalary(any(), any())).willReturn(Collections.emptyList());
//        UserRequestParams params = new UserRequestParams(0.0, 2000.0, 0, 0, null);
//        var response = userService.getUsersBasedOnFilters(params);
//        assertThat(response.getResults()).isEmpty();
//    }

    @Test
    @DisplayName("Should return list correctly when limit is set")
    void limitTest() {
        UserRequestParams params = new UserRequestParams(0.0, 100000.0, 0, 1, null);
        var response = userService.getUsersBasedOnFilters(params);
        assertThat(response.getResults()).hasSize(1);
        assertThat(response.getResults().get(0).getName()).isEqualTo("John");
        assertThat(response.getResults().get(0).getSalary()).isEqualTo(2500.05);
    }

    @Test
    @DisplayName("Should return entire list normally even when limit is larger than number of results")
    void overLimitTest() {
        UserRequestParams params = new UserRequestParams(0.0, 100000.0, 0, 5, null);
        var response = userService.getUsersBasedOnFilters(params);
        assertThat(response.getResults()).hasSize(3);
    }

    @Test
    @DisplayName("Should work normally when limit is set to 0 (No limit)")
    void zeroLimitTest() {
        UserRequestParams params = new UserRequestParams(0.0, 100000.0, 0, 0, null);
        var response = userService.getUsersBasedOnFilters(params);
        assertThat(response.getResults()).hasSize(3);
    }

    @Test
    @DisplayName("Should return list correctly when offset is set")
    void offsetTest() {
        UserRequestParams params = new UserRequestParams(0.0, 100000.0, 1, 0, null);
        var response = userService.getUsersBasedOnFilters(params);
        assertThat(response.getResults()).hasSize(2);
        assertThat(response.getResults().get(0).getName()).isEqualTo("Mary Posa");
        assertThat(response.getResults().get(0).getSalary()).isEqualTo(14000.00);
    }

    @Test
    @DisplayName("Should return empty list when offset is equal to number of results")
    void equalOffsetTest() {
        UserRequestParams params = new UserRequestParams(0.0, 100000.0, 3, 0, null);
        var response = userService.getUsersBasedOnFilters(params);
        assertThat(response.getResults()).isEmpty();
    }

    @Test
    @DisplayName("Should return error when offset is larger than number of results")
    void overOffsetTest() {
        UserRequestParams params = new UserRequestParams(0.0, 100000.0, 4, 0, null);
        assertThatThrownBy(() -> {
            userService.getUsersBasedOnFilters(params);
        }).isInstanceOf(UserException.class).hasMessage("Offset is larger than the number of results");
    }

    @Test
    @DisplayName("Should return in DB order when no sort is specified")
    void noSortTest() {
        UserRequestParams params = new UserRequestParams(0.0, 100000.0, 0, 0, SortCriteria.EMPTY);
        var response = userService.getUsersBasedOnFilters(params);
        assertThat(response.getResults().get(0).getName()).isEqualTo("John");
        assertThat(response.getResults().get(0).getSalary()).isEqualTo(2500.05);
        assertThat(response.getResults().get(2).getName()).isEqualTo("Jane Doe");
        assertThat(response.getResults().get(2).getSalary()).isEqualTo(12345.6);
    }

    @Test
    @DisplayName("Should sort according to name")
    void nameSortTest() {
        UserRequestParams params = new UserRequestParams(0.0, 100000.0, 0, 0, SortCriteria.NAME);
        var response = userService.getUsersBasedOnFilters(params);
        assertThat(response.getResults().get(0).getName()).isEqualTo("Jane Doe");
        assertThat(response.getResults().get(0).getSalary()).isEqualTo(12345.6);
        assertThat(response.getResults().get(1).getName()).isEqualTo("John");
        assertThat(response.getResults().get(1).getSalary()).isEqualTo(2500.05);
        assertThat(response.getResults().get(2).getName()).isEqualTo("Mary Posa");
        assertThat(response.getResults().get(2).getSalary()).isEqualTo(14000.00);
    }

    @Test
    @DisplayName("Should sort according to salary")
    void salarySortTest() {
        UserRequestParams params = new UserRequestParams(0.0, 100000.0, 0, 0, SortCriteria.SALARY);
        var response = userService.getUsersBasedOnFilters(params);
        assertThat(response.getResults().get(0).getName()).isEqualTo("John");
        assertThat(response.getResults().get(0).getSalary()).isEqualTo(2500.05);
        assertThat(response.getResults().get(1).getName()).isEqualTo("Jane Doe");
        assertThat(response.getResults().get(1).getSalary()).isEqualTo(12345.6);
        assertThat(response.getResults().get(2).getName()).isEqualTo("Mary Posa");
        assertThat(response.getResults().get(2).getSalary()).isEqualTo(14000.00);
    }

    private static List<User> mockRepositoryCall() {
        return List.of(new User("John", 2500.05),
                new User("Mary Posa", 14000.00),
                new User("Jane Doe", 12345.6));
    }
}
