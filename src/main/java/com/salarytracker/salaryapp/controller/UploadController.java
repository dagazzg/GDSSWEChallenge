package com.salarytracker.salaryapp.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.salarytracker.salaryapp.controller.model.UploadResponse;
import com.salarytracker.salaryapp.controller.model.UserDTO;
import com.salarytracker.salaryapp.controller.verifier.UserDTOVerifier;
import com.salarytracker.salaryapp.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@RestController
@RequestMapping("/upload")
@Validated
public class UploadController {
    @Autowired
    UploadService uploadService;

    @PostMapping
    public ResponseEntity<UploadResponse> postUploadUserData(@RequestParam("file") MultipartFile file) throws IOException, CsvConstraintViolationException, CsvDataTypeMismatchException {
        uploadService.processCsvFile(file);
        // this is always 1 because 0 is returned by the ExceptionHandler
        return new ResponseEntity<>(new UploadResponse(1), HttpStatus.OK);
    }
}
