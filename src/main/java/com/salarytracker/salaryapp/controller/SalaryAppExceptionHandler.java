package com.salarytracker.salaryapp.controller;

import com.opencsv.exceptions.CsvConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class SalaryAppExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {CsvConstraintViolationException.class})
    ResponseEntity<Object> handleCsvVerifierError(CsvConstraintViolationException ex, WebRequest request) {
        Map<String, Integer> body = new HashMap<>();
        body.put("success", 0);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
