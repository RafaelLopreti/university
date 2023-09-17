package com.lopreti.university.domain.exception;

public class ClassAlreadyExistsException extends RuntimeException {

    public ClassAlreadyExistsException() {}

    public String getCode() {
        return "UNICODE-020";
    }

    public String getMessage() {
        return "Class already exists.";
    }
}
