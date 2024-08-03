package com.lopreti.university.domain.ports.repositories;

import com.lopreti.university.domain.entities.Users;

import java.util.Optional;

public interface UsersRepository {

    Users findByEmail(String email);

    Optional<Users> findToLogin(String email, String password);

}
