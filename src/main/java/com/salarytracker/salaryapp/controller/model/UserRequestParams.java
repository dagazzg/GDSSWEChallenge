package com.salarytracker.salaryapp.controller.model;

import javax.validation.constraints.Min;

public class UserRequestParams {
    @Min(0)
    private Double min = 0.0;
    private Double max = 4000.0;
    @Min(0)
    private Integer offset = 0;
    @Min(1)
    private Integer limit = 0;
    private String sort;

    public UserRequestParams() {
    }

    public UserRequestParams(Double min, Double max, Integer offset, Integer limit, String sort) {
        this.min = min;
        this.max = max;
        this.offset = offset;
        this.limit = limit;
        this.sort = sort;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
