package com.lopreti.university.domain.exception;

public class TeacherNotFoundException extends RuntimeException {

    public TeacherNotFoundException() {}

    public String getCode() {
        return "UNICODE-003";
    }

    public String getMessage() {
        return "Teacher not found.";
    }

}
