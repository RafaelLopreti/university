package com.lopreti.university.domain.exception;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.CLASS_ALREADY_EXISTS_ERROR_CODE;
import static java.lang.String.format;

public class ClassAlreadyExistsException extends RuntimeException {

    protected String classCode;

    public ClassAlreadyExistsException(String classCode) {
        this.classCode = classCode;
    }

    public String getCode() {
        return CLASS_ALREADY_EXISTS_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return format("Class %s already exists.", classCode);
    }
}
