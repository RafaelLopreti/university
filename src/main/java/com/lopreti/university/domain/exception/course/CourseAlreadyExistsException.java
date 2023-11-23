package com.lopreti.university.domain.exception.course;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.COURSE_ALREADY_EXISTS_ERROR_CODE;
import static java.lang.String.format;

public class CourseAlreadyExistsException extends RuntimeException {

    protected String courseName;

    public CourseAlreadyExistsException(String courseName) {
        this.courseName = courseName;
    }

    public String getCode() {
        return COURSE_ALREADY_EXISTS_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return format("Course %s already exists.", courseName);
    }

}
