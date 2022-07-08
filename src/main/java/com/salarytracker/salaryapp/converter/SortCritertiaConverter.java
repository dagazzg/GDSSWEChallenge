package com.salarytracker.salaryapp.converter;

import com.salarytracker.salaryapp.controller.model.SortCriteria;
import org.springframework.core.convert.converter.Converter;

public class SortCritertiaConverter implements Converter<String, SortCriteria> {

    @Override
    public SortCriteria convert(String source) {
        return SortCriteria.valueOf(source.toUpperCase());
    }
}
