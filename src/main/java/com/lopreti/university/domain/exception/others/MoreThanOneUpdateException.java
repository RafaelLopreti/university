package com.lopreti.university.domain.exception.others;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.MORE_THAN_ONE_UPDATE_ERROR_CODE;

public class MoreThanOneUpdateException extends RuntimeException {

    public MoreThanOneUpdateException() {}

    public String getCode() {
        return MORE_THAN_ONE_UPDATE_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return "Only one update allowed at a time.";
    }

}
