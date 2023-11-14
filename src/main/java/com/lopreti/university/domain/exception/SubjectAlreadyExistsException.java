package com.lopreti.university.domain.exception;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.SUBJECT_ALREADY_EXISTS_ERROR_CODE;

public class SubjectAlreadyExistsException extends RuntimeException {

    public SubjectAlreadyExistsException() {}

    public String getCode() {
        return SUBJECT_ALREADY_EXISTS_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return "Subject already exists.";
    }
}
