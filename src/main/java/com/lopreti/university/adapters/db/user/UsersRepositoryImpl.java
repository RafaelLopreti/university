package com.lopreti.university.adapters.db.user;

import com.lopreti.university.domain.entities.Users;
import com.lopreti.university.domain.ports.repositories.UsersRepository;
import com.lopreti.university.domain.valueObjects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepositoryImpl implements UsersRepository {

    @Autowired
    private UsersJpaRepository usersJpaRepository;

    @Override
    public Users findByEmail(Email email) {
        return usersJpaRepository.findByEmail(email);
    }

}
