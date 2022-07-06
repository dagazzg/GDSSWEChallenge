package com.salarytracker.salaryapp.service;

import com.salarytracker.salaryapp.controller.model.UserDTO;
import com.salarytracker.salaryapp.repository.User;
import com.salarytracker.salaryapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadService {
    @Autowired
    UserRepository userRepository;

    public void saveFile(List<UserDTO> userDTOList) {
        List<User> users = userDTOList.stream().map(this::convertToUserRepo).toList();
        users.stream().forEach(user -> userRepository.save(user));
    }

    private User convertToUserRepo(UserDTO userDTO) {
        return new User(userDTO.getName(), userDTO.getSalary());
    }
}
