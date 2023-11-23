package com.lopreti.university.domain.exception.people;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.PEOPLE_NOT_FOUND_ERROR_CODE;
import static java.lang.String.format;

public class PeopleNotFoundException extends RuntimeException {

    protected Long peopleId;

    protected String peopleName;

    protected String fieldName;

    protected Long fieldId;

    public PeopleNotFoundException(Long peopleId) {
        this.peopleId = peopleId;
    }

    public PeopleNotFoundException(String peopleName) {
        this.peopleName = peopleName;
    }

    public PeopleNotFoundException(Long fieldId, String fieldName) {
        this.fieldName = fieldName;
        this.fieldId = fieldId;
    }

    public String getCode() {
        return PEOPLE_NOT_FOUND_ERROR_CODE.getCode();
    }

    public String getMessage() {
        if (peopleId != null) {
            return format("People with id %d not found.", peopleId);
        } else if (peopleName != null) {
            return format("People with name %s not found.", peopleName);
        } else if (fieldName != null && fieldId != null) {
            return format("People with %s %d not found.", fieldName, fieldId);
        } else {
            return "People not found.";
        }
    }

}
