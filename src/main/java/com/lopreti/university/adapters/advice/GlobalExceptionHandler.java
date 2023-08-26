package com.lopreti.university.adapters.advice;

import com.lopreti.university.domain.dtos.ErrorResponseDto;
import com.lopreti.university.domain.exception.*;
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

    @ExceptionHandler(TeacherNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleTeacherNotFoundException(TeacherNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(com.lopreti.university.domain.exception.ClassNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleClassNotFoundException(com.lopreti.university.domain.exception.ClassNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleAddressNotFoundException(AddressNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddressAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleAddressAlreadyExistsException(AddressAlreadyExistsException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MoreThanOneUpdateException.class)
    public ResponseEntity<ErrorResponseDto> handleMoreThanOneUpdateException(MoreThanOneUpdateException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WithoutFieldUpdateException.class)
    public ResponseEntity<ErrorResponseDto> handleWithoutFieldUpdateException(WithoutFieldUpdateException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoValidFieldUpdateException.class)
    public ResponseEntity<ErrorResponseDto> handleNoValidFieldUpdateException(NoValidFieldUpdateException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValueCannotBeEmptyException.class)
    public ResponseEntity<ErrorResponseDto> handleValueCannotBeEmptyException(ValueCannotBeEmptyException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleStudentAlreadyExistsException(StudentAlreadyExistsException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TeacherAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleTeacherAlreadyExistsException(TeacherAlreadyExistsException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailInvalidFormatException.class)
    public ResponseEntity<ErrorResponseDto> handleEmailInvalidFormatException(EmailInvalidFormatException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage(), ex.getAction()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordInvalidFormatException.class)
    public ResponseEntity<ErrorResponseDto> handlePasswordInvalidFormatException(PasswordInvalidFormatException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage(), ex.getAction()), HttpStatus.BAD_REQUEST);
    }

}
