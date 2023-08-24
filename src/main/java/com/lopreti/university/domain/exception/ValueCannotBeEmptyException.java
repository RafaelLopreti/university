package com.lopreti.university.domain.exception;

public class ValueCannotBeEmptyException extends RuntimeException {

    public ValueCannotBeEmptyException() {}

    public String getCode() {
        return "UNICODE-010";
    }

    public String getMessage() {
        return "Value cannot be empty.";
    }

}
