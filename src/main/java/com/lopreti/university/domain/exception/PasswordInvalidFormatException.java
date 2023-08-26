package com.lopreti.university.domain.exception;

public class PasswordInvalidFormatException extends RuntimeException {

    public PasswordInvalidFormatException() {}

    public String getCode() {
        return "UNICODE-016";
    }

    public String getMessage() {
        return "Password invalid format.";
    }

    public String getAction() {
        return "It must have a minimum of 8 characters and a maximum of 20 characters. " +
                "It must contain at least one digit. " +
                "It must contain at least one uppercase alphabet. " +
                "It must contain at least one lowercase alphabet. " +
                "It must contain at least one special character including !@#$%&*()-+=^. " +
                "It must not contain any whitespace.";
    }

}
