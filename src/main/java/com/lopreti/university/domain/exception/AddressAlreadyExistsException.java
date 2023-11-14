package com.lopreti.university.domain.exception;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.ADDRESS_ALREADY_EXISTS_ERROR_CODE;

public class AddressAlreadyExistsException extends RuntimeException {

    public AddressAlreadyExistsException() {}

    public String getCode() {
        return ADDRESS_ALREADY_EXISTS_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return "Address already exists.";
    }

}
