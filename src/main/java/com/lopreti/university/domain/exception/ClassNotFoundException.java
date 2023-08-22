package com.lopreti.university.domain.exception;

public class ClassNotFoundException extends RuntimeException {

    public ClassNotFoundException() {}

    public String getCode() {
        return "UNICODE-005";
    }

    public String getMessage() {
        return "Class not found.";
    }

}
