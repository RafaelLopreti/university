package com.lopreti.university.adapters.db.classes;

import com.lopreti.university.domain.entities.Class;
import com.lopreti.university.domain.ports.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClassRepositoryImpl implements ClassRepository {

    @Autowired
    private ClassJpaRepository classJpaRepository;

    @Override
    public Class findByCode(String classCode) {
        return classJpaRepository.findByCode(classCode);
    }
}
