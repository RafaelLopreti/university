package com.lopreti.university.domain.exception.user;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.USER_NOT_FOUND_ERROR_CODE;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {}

    public String getCode() {
        return USER_NOT_FOUND_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return "User not found.";
    }

}
