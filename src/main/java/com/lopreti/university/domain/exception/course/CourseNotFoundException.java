package com.lopreti.university.domain.exception.course;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.COURSE_NOT_FOUND_ERROR_CODE;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException() {}

    public String getCode() {
        return COURSE_NOT_FOUND_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return "Course not found.";
    }

}
