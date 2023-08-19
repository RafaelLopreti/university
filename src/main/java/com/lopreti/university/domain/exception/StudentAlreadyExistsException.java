package com.lopreti.university.domain.exception;

public class StudentAlreadyExistsException extends RuntimeException {

    public StudentAlreadyExistsException() {}

    public String getCode() {
        return "UNICODE-002";
    }

    public String getMessage() {
        return "Student already exists.";
    }

}
