package com.lopreti.university.domain.exception.others;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.WITHOUT_FIELD_UPDATE_ERROR_CODE;

public class WithoutFieldUpdateException extends RuntimeException {

    public WithoutFieldUpdateException() {}

    public String getCode() {
        return WITHOUT_FIELD_UPDATE_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return "Enter a field to update.";
    }

}
