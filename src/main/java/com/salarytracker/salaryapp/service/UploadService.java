package com.salarytracker.salaryapp.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.salarytracker.salaryapp.controller.model.UserDTO;
import com.salarytracker.salaryapp.controller.verifier.UserDTOVerifier;
import com.salarytracker.salaryapp.repository.User;
import com.salarytracker.salaryapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class UploadService {
    @Autowired
    UserRepository userRepository;

    public void uploadUsersUsingCsv(MultipartFile file) throws IOException, CsvConstraintViolationException, CsvDataTypeMismatchException {
        List<UserDTO> userDTOList = processCsv(file);
        saveValidUsers(userDTOList);
    }

    private List<UserDTO> processCsv(MultipartFile file) throws IOException, CsvConstraintViolationException, CsvDataTypeMismatchException {
        List<UserDTO> userDTOList;

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<UserDTO> csvToBean = new CsvToBeanBuilder<UserDTO>(reader)
                    .withType(UserDTO.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withVerifier(new UserDTOVerifier())
                    .build();

            userDTOList = csvToBean.parse();
        }

        return userDTOList;
    }

    private void saveValidUsers(List<UserDTO> userDTOList) {
        List<User> users = userDTOList.stream().map(this::convertToUserRepo).toList();
        users.stream().forEach(user -> userRepository.save(user));
    }

    private User convertToUserRepo(UserDTO userDTO) {
        return new User(userDTO.getName(), userDTO.getSalary());
    }

}
