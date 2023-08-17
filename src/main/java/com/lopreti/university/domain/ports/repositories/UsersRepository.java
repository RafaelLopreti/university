package com.lopreti.university.domain.ports.repositories;

import com.lopreti.university.domain.entities.Users;
import com.lopreti.university.domain.valueObjects.Email;

public interface UsersRepository {

    public Users findByEmail(Email email);

}
