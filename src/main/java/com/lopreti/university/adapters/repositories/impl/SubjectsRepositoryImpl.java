package com.lopreti.university.adapters.repositories.impl;

import com.lopreti.university.adapters.repositories.jpa.SubjectsJpaRepository;
import com.lopreti.university.domain.entities.Subjects;
import com.lopreti.university.domain.exception.SubjectsNotFoundException;
import com.lopreti.university.domain.ports.repositories.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SubjectsRepositoryImpl implements SubjectsRepository {

    @Autowired
    private SubjectsJpaRepository subjectsJpaRepository;

    public Subjects findById(Long id) {
        return subjectsJpaRepository.findById(id).orElseThrow(() -> new SubjectsNotFoundException(id));
    }

    public List<Subjects> findAll() {
        return subjectsJpaRepository.findAll();
    }

    public Subjects save(Subjects subjects) {
        return subjectsJpaRepository.save(subjects);
    }

    @Override
    public Subjects findByName(String name) {
        return subjectsJpaRepository.findByName(name);
    }

    public boolean existsById(Long id) {
        return subjectsJpaRepository.existsById(id);
    }

    public Subjects existsByName(String name) {
        return subjectsJpaRepository.findByName(name);
    }
}
