package com.lopreti.university.domain.exception.student;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.STUDENT_NOT_FOUND_ERROR_CODE;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException() {}

    public String getCode() {
        return STUDENT_NOT_FOUND_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return "Student not found.";
    }

}
