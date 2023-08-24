package com.lopreti.university.domain.exception;

import static java.lang.String.format;

public class NoValidFieldUpdateException extends RuntimeException {

    private String field;

    public NoValidFieldUpdateException(String field) {
        this.field = field;
    }

    public String getCode() {
        return "UNICODE-009";
    }

    public String getMessage() {
        return format("%s not a valid field to update", field);
    }

}
