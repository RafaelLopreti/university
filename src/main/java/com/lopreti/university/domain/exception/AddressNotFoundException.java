package com.lopreti.university.domain.exception;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.ADDRESS_NOT_FOUND_ERROR_CODE;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException() {
    }

    public String getCode() {
        return ADDRESS_NOT_FOUND_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return "Address not found.";
    }

}
