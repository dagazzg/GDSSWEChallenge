package com.salarytracker.salaryapp.controller;

import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.salarytracker.salaryapp.controller.model.UploadResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SalaryAppExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {CsvConstraintViolationException.class, CsvDataTypeMismatchException.class})
    ResponseEntity<Object> handleCsvVerifierError(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new UploadResponse(0), HttpStatus.BAD_REQUEST);
    }
}
