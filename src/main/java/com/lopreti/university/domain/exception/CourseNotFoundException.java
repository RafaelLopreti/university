package com.lopreti.university.domain.exception;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException() {}

    public String getCode() {
        return "UNICODE-018";
    }

    public String getMessage() {
        return "Course not found.";
    }

}
