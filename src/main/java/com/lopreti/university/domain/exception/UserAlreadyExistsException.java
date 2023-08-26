package com.lopreti.university.domain.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {}

    public String getCode() {
        return "UNICODE-012";
    }

    public String getMessage() {
        return "User already exists.";
    }

}
