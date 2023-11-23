package com.lopreti.university.domain.exception.user.password;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.PASSWORD_INVALID_FORMAT_ERROR_CODE;

public class PasswordInvalidFormatException extends RuntimeException {

    public PasswordInvalidFormatException() {}

    public String getCode() {
        return PASSWORD_INVALID_FORMAT_ERROR_CODE.getCode();
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
