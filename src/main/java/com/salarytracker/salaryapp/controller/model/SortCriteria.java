package com.salarytracker.salaryapp.controller.model;

public enum SortCriteria {
    NAME("name"),
    SALARY("salary"),
    EMPTY("");

    final String sortName;

    SortCriteria(String sortName) {
        this.sortName = sortName;
    }

    @Override
    public String toString() {
        return sortName == null ? super.toString() : sortName;
    }
}
