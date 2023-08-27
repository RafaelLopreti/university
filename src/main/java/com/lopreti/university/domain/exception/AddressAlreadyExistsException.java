package com.lopreti.university.domain.exception;

public class AddressAlreadyExistsException extends RuntimeException {

    public AddressAlreadyExistsException() {}

    public String getCode() {
        return "UNICODE-011";
    }

    public String getMessage() {
        return "Address already exists.";
    }

}
