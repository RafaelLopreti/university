package com.lopreti.university.domain.exception;

public class CourseAlreadyExistsException extends RuntimeException {

    public CourseAlreadyExistsException() {}

    public String getCode() {
        return "UNICODE-017";
    }

    public String getMessage() {
        return "Course already exists.";
    }

}
