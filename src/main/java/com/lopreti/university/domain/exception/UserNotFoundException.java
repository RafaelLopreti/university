package com.lopreti.university.domain.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {}

    public String getCode() {
        return "UNICODE-013";
    }

    public String getMessage() {
        return "User not found.";
    }

}
