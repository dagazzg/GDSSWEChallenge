package com.salarytracker.salaryapp.controller.verifier;

import com.opencsv.bean.BeanVerifier;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.salarytracker.salaryapp.controller.model.UserDTO;

public class UserDTOVerifier implements BeanVerifier<UserDTO> {

    @Override
    public boolean verifyBean(UserDTO userDTO) throws CsvConstraintViolationException {
        if (userDTO.getName().isEmpty()) {
            throw new CsvConstraintViolationException("Data in CSV is invalid");
        }

        if (userDTO.getSalary() < 0.0) {
            return false;
        }
        return true;
    }
}
