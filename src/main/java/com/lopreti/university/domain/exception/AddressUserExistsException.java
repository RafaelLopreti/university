package com.lopreti.university.domain.exception;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.ADDRESS_USER_ALREADY_EXISTS_ERROR_CODE;
import static java.lang.String.format;

public class AddressUserExistsException extends RuntimeException {

    protected Long id;

    public AddressUserExistsException(Long id) {
        this.id = id;
    }

    public AddressUserExistsException() {}

    public String getCode() {
        return ADDRESS_USER_ALREADY_EXISTS_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return format("This address with id %s is already registered to a user.", id);
    }

}
