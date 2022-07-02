package com.salarytracker.salaryapp.repository;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserDAO> findBySalary(Long min, Long max);
}
