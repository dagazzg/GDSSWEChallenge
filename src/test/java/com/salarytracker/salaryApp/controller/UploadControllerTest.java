package com.salarytracker.salaryApp.controller;

import com.salarytracker.salaryApp.service.UploadService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UploadController.class)
public class UploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    UploadService uploadService;

    @Test
    @WithMockUser(username = "admin", password = "test1234", roles = "ADMIN")
    public void postUploadTest() throws Exception {
        mockMvc.perform(post("/upload")
                        .contentType("multipart/form-data")
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}
