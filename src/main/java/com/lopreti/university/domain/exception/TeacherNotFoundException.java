package com.lopreti.university.domain.exception;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.TEACHER_NOT_FOUND_ERROR_CODE;
import static java.lang.String.format;

public class TeacherNotFoundException extends RuntimeException {

    protected Long teacherId;

    public TeacherNotFoundException(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getCode() {
        return TEACHER_NOT_FOUND_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return format("Teacher with id %s not found.", teacherId);
    }

}
