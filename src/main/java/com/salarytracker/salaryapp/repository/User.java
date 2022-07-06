package com.salarytracker.salaryapp.repository;

import javax.persistence.*;

@Entity
@Table(name = "appuser")
public class User {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private double salary;

    protected User() {
    }

    public User(String name, double salary) {
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

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
