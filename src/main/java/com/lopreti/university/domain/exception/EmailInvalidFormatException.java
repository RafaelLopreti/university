package com.lopreti.university.domain.exception;

public class EmailInvalidFormatException extends RuntimeException {

    public EmailInvalidFormatException() {}

    public String getCode() {
        return "UNICODE-015";
    }

    public String getMessage() {
        return "E-mail invalid format.";
    }

    public String getAction() {
        return "Numeric values from 0 to 9 are allowed. " +
                "Uppercase and lowercase letters from a to z are permitted. " +
                "Underscore (_), hyphen (-), and period (.) are allowed. " +
                "Period (.) is not allowed at the beginning and end of the local part. " +
                "Consecutive periods are not allowed. " +
                "For the local part, a maximum of 64 characters is allowed.";
    }

}
