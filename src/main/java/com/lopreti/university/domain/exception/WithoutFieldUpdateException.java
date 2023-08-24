package com.lopreti.university.domain.exception;

public class WithoutFieldUpdateException extends RuntimeException {

    public WithoutFieldUpdateException() {}

    public String getCode() {
        return "UNICODE-008";
    }

    public String getMessage() {
        return "Enter a field to update.";
    }

}
