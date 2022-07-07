package com.salarytracker.salaryapp.service;

import com.salarytracker.salaryapp.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

class UploadServiceTest {
    @Mock
    UserRepository userRepository;

    @Autowired
    UploadService uploadService;

    @Test
    @DisplayName("Should not insert anything when there is an invalid record")
    void invalidRecordInDataTest() {

    }
}
