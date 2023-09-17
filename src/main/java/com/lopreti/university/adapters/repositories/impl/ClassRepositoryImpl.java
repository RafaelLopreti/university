package com.lopreti.university.adapters.repositories.impl;

import com.lopreti.university.adapters.repositories.jpa.ClassJpaRepository;
import com.lopreti.university.domain.entities.Class;
import com.lopreti.university.domain.exception.ClassNotFoundException;
import com.lopreti.university.domain.ports.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClassRepositoryImpl implements ClassRepository {

    @Autowired
    private ClassJpaRepository classJpaRepository;

    public Class findById(Long id) {
        return classJpaRepository.findById(id).orElseThrow(ClassNotFoundException::new);
    }

    public List<Class> findAll() {
        return classJpaRepository.findAll();
    }

    @Override
    public Class findByCode(String classCode) {
        return classJpaRepository.findByCode(classCode);
    }

    public Class save(Class classes) {
        return classJpaRepository.save(classes);
    }

    public Optional<Class> existsByCode(String classCode) {
        return classJpaRepository.existsByCode(classCode);
    }

    public boolean existsById(Long id) {
        return classJpaRepository.existsById(id);
    }

}
