package com.salarytracker.salaryapp.controller.model;

import com.opencsv.bean.CsvBindByName;

public class UserDTO {
    @CsvBindByName(column = "Name")
    private String name;
    @CsvBindByName(column = "Salary")
    private double salary;

    public UserDTO() {
    }

    public UserDTO(String name, double salary) {
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
}
