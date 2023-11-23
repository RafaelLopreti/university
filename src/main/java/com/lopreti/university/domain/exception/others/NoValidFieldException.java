package com.lopreti.university.domain.exception.others;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.NO_VALID_FIELD_ERROR_CODE;
import static java.lang.String.format;

public class NoValidFieldException extends RuntimeException {

    protected String field;

    public NoValidFieldException(String field) {
        this.field = field;
    }

    public String getCode() {
        return NO_VALID_FIELD_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return format("%s not a valid field.", field);
    }

}
