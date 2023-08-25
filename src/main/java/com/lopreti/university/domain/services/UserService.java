package com.lopreti.university.domain.services;

import com.lopreti.university.adapters.repositories.impl.UsersRepositoryImpl;
import com.lopreti.university.domain.entities.Users;
import com.lopreti.university.domain.exception.NoValidFieldUpdateException;
import com.lopreti.university.domain.exception.TeacherAlreadyExistsException;
import com.lopreti.university.domain.exception.ValueCannotBeEmptyException;
import com.lopreti.university.domain.valueObjects.Email;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UsersRepositoryImpl usersRepository;

    public UserService(UsersRepositoryImpl usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users findById(Long id) {
        return usersRepository.findById(id);
    }

    public List<Users> findAll(){
        return usersRepository.findAll();
    }

    public Users findByEmail(Email email) {
        return usersRepository.findByEmail(email);
    }

}
