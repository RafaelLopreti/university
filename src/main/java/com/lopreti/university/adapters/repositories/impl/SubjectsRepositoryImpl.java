package com.lopreti.university.adapters.repositories.impl;

import com.lopreti.university.adapters.repositories.jpa.SubjectsJpaRepository;
import com.lopreti.university.domain.entities.People;
import com.lopreti.university.domain.entities.Subjects;
import com.lopreti.university.domain.exception.PeopleNotFoundException;
import com.lopreti.university.domain.ports.repositories.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectsRepositoryImpl implements SubjectsRepository {

    @Autowired
    private SubjectsJpaRepository subjectsJpaRepository;

    public Subjects findById(Long id) {
        return subjectsJpaRepository.findById(id).orElseThrow(PeopleNotFoundException::new);
    }

    @Override
    public Subjects findByName(String name) {
        return subjectsJpaRepository.findByName(name);
    }
}
