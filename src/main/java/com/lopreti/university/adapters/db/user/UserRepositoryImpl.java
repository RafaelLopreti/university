package com.lopreti.university.adapters.db.user;

import com.lopreti.university.domain.entities.User;
import com.lopreti.university.domain.ports.repositories.UserRepository;
import com.lopreti.university.domain.valueObjects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public User findByEmail(Email email) {
        return userJpaRepository.findByEmail(email);
    }

}
