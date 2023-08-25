package com.lopreti.university.adapters.repositories.impl;

import com.lopreti.university.adapters.repositories.jpa.UsersJpaRepository;
import com.lopreti.university.domain.entities.Users;
import com.lopreti.university.domain.ports.repositories.UsersRepository;
import com.lopreti.university.domain.valueObjects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsersRepositoryImpl implements UsersRepository {

    @Autowired
    private UsersJpaRepository usersJpaRepository;

    @Override
    public Users findByEmail(Email email) {
        return usersJpaRepository.findByEmail(email);
    }

    public Users findById(Long id) {
        return usersJpaRepository.findById(id).orElseThrow(); // TODO USER NOT FOUND EXCEPTION
    }

    public List<Users> findAll() {
        return usersJpaRepository.findAll();
    }

}
