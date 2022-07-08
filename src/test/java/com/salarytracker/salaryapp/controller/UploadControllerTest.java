package com.salarytracker.salaryapp.controller;

import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.salarytracker.salaryapp.service.UploadService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.FileInputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UploadController.class)
@WithMockUser(username = "admin", password = "test1234", roles = "ADMIN")
class UploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UploadService uploadService;

    @Test
    @DisplayName("Uploading a valid csv file should return OK")
    void postUploadTest() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/goodData.csv");
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", fileInputStream);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
                .file(mockMultipartFile)
                .contentType("multipart/form-data")
                .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Uploading file with invalid name should return 400")
    void rejectDataTest() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/rejectNameData.csv");
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", fileInputStream);
        doThrow(new CsvConstraintViolationException()).when(uploadService).uploadUsersUsingCsv(any());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
                        .file(mockMultipartFile)
                        .contentType("multipart/form-data")
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Uploading file with negative salary should still be accepted")
    void invalidSalaryDataTest() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/invalidSalaryData.csv");
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", fileInputStream);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
                        .file(mockMultipartFile)
                        .contentType("multipart/form-data")
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Uploading file with non-number salary should return 400")
    void rejectSalaryDataTest() throws Exception {
        doThrow(new CsvDataTypeMismatchException()).when(uploadService).uploadUsersUsingCsv(any());
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/rejectSalaryData.csv");
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", fileInputStream);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
                        .file(mockMultipartFile)
                        .contentType("multipart/form-data")
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }
}
