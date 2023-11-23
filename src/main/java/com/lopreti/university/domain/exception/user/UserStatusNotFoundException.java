package com.lopreti.university.domain.exception.user;

import com.lopreti.university.domain.valueObjects.UserStatus;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.USER_STATUS_NOT_FOUND_ERROR_CODE;
import static java.lang.String.format;

public class UserStatusNotFoundException extends RuntimeException {

    protected UserStatus status;

    public UserStatusNotFoundException(UserStatus status) {
        this.status = status;
    }

    public UserStatusNotFoundException() {}

    public String getCode() {
        return USER_STATUS_NOT_FOUND_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return format("User status %s not found.", status);
    }

}
