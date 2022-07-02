package com.salarytracker.salaryapp.repository;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "salaryApp")
public class UserDAO {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private Long salary;

    protected UserDAO() {
    }

    public UserDAO(String name, Long salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "UserDAO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
