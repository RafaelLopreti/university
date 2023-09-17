package com.lopreti.university.domain.exception;

public class SubjectsNotFoundException extends RuntimeException {

    public SubjectsNotFoundException() {}

    public String getCode() {
        return "UNICODE-022";
    }

    public String getMessage() {
        return "Subject not found.";
    }

}
