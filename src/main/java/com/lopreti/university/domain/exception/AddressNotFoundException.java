package com.lopreti.university.domain.exception;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException() {}

    public String getCode() {
        return "UNICODE-006";
    }

    public String getMessage() {
        return "Address not found.";
    }

}
