package com.lopreti.university.domain.exception.user.email;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.EMAIL_ALREADY_EXISTS_ERROR_CODE;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException() {}

    public String getCode() {
        return EMAIL_ALREADY_EXISTS_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return "E-mail already exists.";
    }

}
