package com.salarytracker.salaryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    <T extends User> T save(T user);
}
