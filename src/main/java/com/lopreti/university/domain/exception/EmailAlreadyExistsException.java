package com.lopreti.university.domain.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException() {}

    public String getCode() {
        return "UNICODE-014";
    }

    public String getMessage() {
        return "E-mail already exists.";
    }

}
