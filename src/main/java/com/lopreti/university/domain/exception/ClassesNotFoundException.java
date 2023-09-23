package com.lopreti.university.domain.exception;

public class ClassesNotFoundException extends RuntimeException {

    public ClassesNotFoundException() {}

    public String getCode() {
        return "UNICODE-005";
    }

    public String getMessage() {
        return "Class not found.";
    }

}
