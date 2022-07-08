package com.salarytracker.salaryapp.service;

import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.salarytracker.salaryapp.repository.User;
import com.salarytracker.salaryapp.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UploadServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UploadService uploadService;

    @Test
    @DisplayName("Should insert everything when csv is valid")
    void allValidDataTest() throws IOException, CsvConstraintViolationException, CsvDataTypeMismatchException {
        given(userRepository.save(any())).willReturn(new User("test", 123.4));
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/goodData.csv");
        MultipartFile multipartFile = new MockMultipartFile("file", fileInputStream);

        uploadService.uploadUsersUsingCsv(multipartFile);
        verify(userRepository, times(3)).save(any());
    }

    @Test
    @DisplayName("Should not insert anything when a name in csv is invalid")
    void rejectedNameInDataTest() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/rejectNameData.csv");
        MultipartFile multipartFile = new MockMultipartFile("file", fileInputStream);

        assertThatThrownBy(() -> {
            uploadService.uploadUsersUsingCsv(multipartFile);
        }).isInstanceOf(RuntimeException.class).hasMessageContaining("Error parsing CSV");
        verify(userRepository, times(0)).save(any());
    }

    @Test
    @DisplayName("Should not insert anything when a salary in csv is invalid")
    void rejectedSalaryInDataTest() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/rejectSalaryData.csv");
        MultipartFile multipartFile = new MockMultipartFile("file", fileInputStream);

        assertThatThrownBy(() -> {
            uploadService.uploadUsersUsingCsv(multipartFile);
        }).isInstanceOf(RuntimeException.class).hasMessageContaining("Error parsing CSV");
        verify(userRepository, times(0)).save(any());
    }

    @Test
    @DisplayName("Should ignore rows when a salary in csv is negative")
    void invalidSalaryInDataTest() throws IOException, CsvConstraintViolationException, CsvDataTypeMismatchException {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/invalidSalaryData.csv");
        MultipartFile multipartFile = new MockMultipartFile("file", fileInputStream);

        uploadService.uploadUsersUsingCsv(multipartFile);
        verify(userRepository, times(1)).save(any());
    }
}
