package com.lopreti.university.domain.ports.repositories;

import com.lopreti.university.domain.entities.Users;

public interface UsersRepository {

    public Users findByEmail(String email);

}
