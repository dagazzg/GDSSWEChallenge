package com.salarytracker.salaryapp.controller;

import com.salarytracker.salaryapp.service.UploadService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.FileInputStream;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void badDataTest() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/badData.csv");
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", fileInputStream);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
                        .file(mockMultipartFile)
                        .contentType("multipart/form-data")
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }
}
