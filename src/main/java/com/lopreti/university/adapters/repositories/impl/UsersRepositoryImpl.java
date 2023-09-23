package com.lopreti.university.adapters.repositories.impl;

import com.lopreti.university.adapters.repositories.jpa.UsersJpaRepository;
import com.lopreti.university.domain.entities.Users;
import com.lopreti.university.domain.exception.UserNotFoundException;
import com.lopreti.university.domain.ports.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsersRepositoryImpl implements UsersRepository {

    @Autowired
    private UsersJpaRepository usersJpaRepository;

    @Override
    public Users findByEmail(String email) {
        return usersJpaRepository.findByEmail(email);
    }

    public Users findById(Long id) {
        return usersJpaRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public List<Users> findAll() {
        return usersJpaRepository.findAll();
    }

    public Users save(Users user) {
        return usersJpaRepository.save(user);
    }

    public boolean existsById(Long id) {
        return usersJpaRepository.existsById(id);
    }

    public Optional<String> existsByEmail(String email) {
        return usersJpaRepository.existsByEmail(email);
    }

}
