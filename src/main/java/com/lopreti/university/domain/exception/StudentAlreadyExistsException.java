package com.lopreti.university.domain.exception;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.STUDENT_ALREADY_EXISTS_ERROR_CODE;

public class StudentAlreadyExistsException extends RuntimeException {

    public StudentAlreadyExistsException() {}

    public String getCode() {
        return STUDENT_ALREADY_EXISTS_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return "Student already exists.";
    }

}
