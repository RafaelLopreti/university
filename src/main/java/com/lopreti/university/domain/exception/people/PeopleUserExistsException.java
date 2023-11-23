package com.lopreti.university.domain.exception.people;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.USER_PEOPLE_ALREADY_EXISTS_ERROR_CODE;
import static java.lang.String.format;

public class PeopleUserExistsException extends RuntimeException {

    protected Long id;

    public PeopleUserExistsException(Long id) {
        this.id = id;
    }

    public PeopleUserExistsException() {}

    public String getCode() {
        return USER_PEOPLE_ALREADY_EXISTS_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return format("This user with id %s is already registered to a person.", id);
    }

}
