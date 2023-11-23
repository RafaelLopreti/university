package com.lopreti.university.domain.exception.user;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.USER_ADDRESS_ALREADY_EXISTS_ERROR_CODE;
import static java.lang.String.format;

public class UserAddressExistsException extends RuntimeException {

    protected Long id;

    public UserAddressExistsException(Long id) {
        this.id = id;
    }

    public UserAddressExistsException() {}

    public String getCode() {
        return USER_ADDRESS_ALREADY_EXISTS_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return format("This user with id %s is already registered at an address.", id);
    }

}
