package com.lopreti.university.adapters.advice;

import com.lopreti.university.domain.dtos.ErrorResponseDto;
import com.lopreti.university.domain.exception.StudentAlreadyExistsException;
import com.lopreti.university.domain.exception.StudentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleStudentNotFoundException(StudentNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleStudentAlreadyExistsException(StudentAlreadyExistsException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
