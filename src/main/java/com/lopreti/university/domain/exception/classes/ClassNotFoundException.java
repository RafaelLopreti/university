package com.lopreti.university.domain.exception.classes;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.CLASSES_NOT_FOUND_ERROR_CODE;
import static java.lang.String.format;

public class ClassNotFoundException extends RuntimeException {

    protected String classCode;

    protected Long classId;

    public ClassNotFoundException(String classCode) {
        this.classCode = classCode;
    }

    public ClassNotFoundException(Long classId) {
        this.classId = classId;
    }

    public String getCode() {
        return CLASSES_NOT_FOUND_ERROR_CODE.getCode();
    }

    public String getMessage() {
        if (classCode != null) {
            return format("Class with code %s not found.", classCode);
        } else if (classId != null) {
            return format("Class with id %d not found.", classId);
        } else {
            return "Class not found.";
        }
    }

}
