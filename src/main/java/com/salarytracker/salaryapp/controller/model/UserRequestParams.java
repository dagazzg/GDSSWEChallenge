package com.salarytracker.salaryapp.controller.model;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;


public class UserRequestParams {
    @DecimalMin("0.0")
    private double min;
    @DecimalMin("0.0")
    private double max = 4000.0;
    @Min(0)
    private int offset;
    @Min(0)
    private int limit;
    //TODO: Handle illegal sortcriteria
    private SortCriteria sort;

    public UserRequestParams() {
    }

    public UserRequestParams(double min, double max, int offset, int limit, SortCriteria sortCriteria) {
        this.min = min;
        this.max = max;
        this.offset = offset;
        this.limit = limit;
        this.sort = sortCriteria;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public SortCriteria getSort() {
        return sort;
    }

    public void setSort(SortCriteria sort) {
        this.sort = sort;
    }
}
