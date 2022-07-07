package com.salarytracker.salaryapp.controller.verifier;

import com.opencsv.bean.BeanVerifier;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.salarytracker.salaryapp.controller.model.UserDTO;

public class UserDTOVerifier implements BeanVerifier<UserDTO> {

    @Override
    public boolean verifyBean(UserDTO userDTO) throws CsvConstraintViolationException {
        if (userDTO.getName().isEmpty() || userDTO.getSalary() < 0.0) {
            throw new CsvConstraintViolationException("Data in CSV is invalid");
        }
        return true;
    }
}
