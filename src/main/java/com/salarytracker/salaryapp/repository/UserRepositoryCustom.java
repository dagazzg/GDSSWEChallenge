package com.salarytracker.salaryapp.repository;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findBySalary(Long min, Long max);
}
