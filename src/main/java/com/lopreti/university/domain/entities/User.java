package com.lopreti.university.domain.entities;

import com.lopreti.university.domain.valueObjects.Email;
import com.lopreti.university.domain.valueObjects.Password;
import com.lopreti.university.domain.valueObjects.UserStatus;

public class User {

    private Long id;

    private Email email;

    private Password password;

    private UserStatus status = UserStatus.ACTIVE;

}
