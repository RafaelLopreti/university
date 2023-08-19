package com.lopreti.university.adapters.repositories.impl;

import com.lopreti.university.adapters.repositories.jpa.TeacherJpaRepository;
import com.lopreti.university.domain.entities.Teacher;
import com.lopreti.university.domain.ports.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherRepositoryImpl implements TeacherRepository {

    @Autowired
    private TeacherJpaRepository teacherJpaRepository;

    @Override
    public List<Teacher> findByClass(String classCode) {
        return teacherJpaRepository.findByClass(classCode);
    }

}
