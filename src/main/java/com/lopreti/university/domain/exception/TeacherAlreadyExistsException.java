package com.lopreti.university.domain.exception;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.TEACHER_ALREADY_EXISTS_ERROR_CODE;

public class TeacherAlreadyExistsException extends RuntimeException {

    public TeacherAlreadyExistsException() {}

    public String getCode() {
        return TEACHER_ALREADY_EXISTS_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return "Teacher already exists.";
    }

}
