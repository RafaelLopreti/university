package com.lopreti.university.domain.ports.repositories;

import com.lopreti.university.domain.entities.User;
import com.lopreti.university.domain.valueObjects.Email;

public interface UserRepository {

    public User findByEmail(Email email);

}
