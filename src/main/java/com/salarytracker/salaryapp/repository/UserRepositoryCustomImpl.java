package com.salarytracker.salaryapp.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<UserDAO> findBySalary(Long min, Long max) {
        return null;
    }
}
