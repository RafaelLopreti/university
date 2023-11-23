package com.lopreti.university.domain.exception.user;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.USER_IS_REQUIRED_ERROR_CODE;

public class UserIsRequiredException extends RuntimeException {

    public UserIsRequiredException() {}

    public String getCode() {
        return USER_IS_REQUIRED_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return "User is required.";
    }

}
