package com.lopreti.university.adapters.repositories.impl;

import com.lopreti.university.adapters.repositories.jpa.ClassJpaRepository;
import com.lopreti.university.domain.entities.Classes;
import com.lopreti.university.domain.exception.ClassesNotFoundException;
import com.lopreti.university.domain.ports.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClassRepositoryImpl implements ClassRepository {

    @Autowired
    private ClassJpaRepository classJpaRepository;

    public Classes findById(Long id) {
        return classJpaRepository.findById(id).orElseThrow(() -> new ClassesNotFoundException(id));
    }

    public List<Classes> findAll() {
        return classJpaRepository.findAll();
    }

    @Override
    public Classes findByCode(String classCode) {
        return classJpaRepository.findByCode(classCode);
    }

    public Classes save(Classes classes) {
        return classJpaRepository.save(classes);
    }

    public Optional<Classes> existsByCode(String classCode) {
        return classJpaRepository.existsByCode(classCode);
    }

    public boolean existsById(Long id) {
        return classJpaRepository.existsById(id);
    }

}
