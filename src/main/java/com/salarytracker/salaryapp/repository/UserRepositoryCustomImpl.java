package com.salarytracker.salaryapp.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> findBySalary(double min, double max) {
        Query query = entityManager.createNativeQuery("SELECT * FROM appuser " +
                "WHERE appuser.salary >= ? AND " +
                "appuser.salary <= ?", User.class);
        query.setParameter(1, min);
        query.setParameter(2, max);
        return query.getResultList();
    }
}
