package com.lopreti.university.domain.exception;

public class MoreThanOneUpdateException extends RuntimeException {

    public MoreThanOneUpdateException() {}

    public String getCode() {
        return "UNICODE-007";
    }

    public String getMessage() {
        return "Only one update allowed at a time.";
    }

}
