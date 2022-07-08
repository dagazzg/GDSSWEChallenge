package com.salarytracker.salaryapp.service;

import com.salarytracker.salaryapp.controller.model.SortCriteria;
import com.salarytracker.salaryapp.controller.model.UserDTO;
import com.salarytracker.salaryapp.controller.model.UserRequestParams;
import com.salarytracker.salaryapp.controller.model.UserResponse;
import com.salarytracker.salaryapp.exception.UserException;
import com.salarytracker.salaryapp.repository.User;
import com.salarytracker.salaryapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponse getUsersBasedOnFilters(UserRequestParams userRequestParams) {
        List<User> userList = userRepository.findBySalary(userRequestParams.getMin(), userRequestParams.getMax());
        List<UserDTO> userDTOList = userList.stream().map(this::convertToUserDTO).toList();

        int offset = userRequestParams.getOffset();
        if (offset > userDTOList.size()) {
            throw new UserException("Offset is larger than the number of results");
        }
        if (offset > 0)
            userDTOList = userDTOList.subList(offset, userDTOList.size());
        int limit = userRequestParams.getLimit() > userDTOList.size()
                || userRequestParams.getLimit() == 0
                ? userDTOList.size() : userRequestParams.getLimit();
        userDTOList = userDTOList.subList(0, limit);
        userDTOList = sortResults(userDTOList, userRequestParams.getSort());
        return new UserResponse(userDTOList);
    }

    private UserDTO convertToUserDTO(User user) {
        return new UserDTO(user.getName(), user.getSalary());
    }

    private List<UserDTO> sortResults(List<UserDTO> userDTOList, SortCriteria sortCriteria) {
        if (sortCriteria != null) {
            switch(sortCriteria) {
                case NAME:
                    userDTOList = userDTOList.stream().sorted(Comparator.comparing(UserDTO::getName)).toList();
                    break;

                case SALARY:
                    userDTOList = userDTOList.stream().sorted(Comparator.comparing(UserDTO::getSalary)).toList();
                    break;

                default:
                    break;
            }
        }

        return userDTOList;
    }
}
