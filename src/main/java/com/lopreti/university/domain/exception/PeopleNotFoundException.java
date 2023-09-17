package com.lopreti.university.domain.exception;

public class PeopleNotFoundException extends RuntimeException {

    public PeopleNotFoundException() {}

    public String getCode() {
        return "UNICODE-021";
    }

    public String getMessage() {
        return "People not found.";
    }

}
