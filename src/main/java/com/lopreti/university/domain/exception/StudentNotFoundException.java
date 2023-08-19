package com.lopreti.university.domain.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException() {}

    public String getCode() {
        return "UNICODE-001";
    }

    public String getMessage() {
        return "Student not found.";
    }

}
