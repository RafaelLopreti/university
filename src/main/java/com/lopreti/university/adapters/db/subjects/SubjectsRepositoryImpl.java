package com.lopreti.university.adapters.db.subjects;

import com.lopreti.university.domain.entities.Subjects;
import com.lopreti.university.domain.ports.repositories.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectsRepositoryImpl implements SubjectsRepository {

    @Autowired
    private SubjectsJpaRepository subjectsJpaRepository;

    @Override
    public Subjects findByName(String name) {
        return subjectsJpaRepository.findByName(name);
    }
}
