package com.lopreti.university.domain.exception;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.VALID_CANNOT_BE_EMPTY_ERROR_CODE;

public class ValueCannotBeEmptyException extends RuntimeException {

    public ValueCannotBeEmptyException() {}

    public String getCode() {
        return VALID_CANNOT_BE_EMPTY_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return "Value cannot be empty.";
    }

}
