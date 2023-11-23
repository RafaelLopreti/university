package com.lopreti.university.domain.exception.people;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.PEOPLE_ALREADY_EXISTS_ERROR_CODE;

public class PeopleAlreadyExistsException extends RuntimeException {

    public PeopleAlreadyExistsException() {}

    public String getCode() {
        return PEOPLE_ALREADY_EXISTS_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return "People already exists.";
    }

}
