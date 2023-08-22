package com.lopreti.university.domain.exception;

public class TeacherAlreadyExistsException extends RuntimeException {

    public TeacherAlreadyExistsException() {}

    public String getCode() {
        return "UNICODE-004";
    }

    public String getMessage() {
        return "Teacher already exists.";
    }

}
