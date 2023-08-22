package com.lopreti.university.adapters.repositories.impl;

import com.lopreti.university.adapters.repositories.jpa.TeacherJpaRepository;
import com.lopreti.university.domain.entities.Teacher;
import com.lopreti.university.domain.exception.StudentNotFoundException;
import com.lopreti.university.domain.exception.TeacherNotFoundException;
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

    public Teacher findById(Long id) {
        return teacherJpaRepository.findById(id).orElseThrow(TeacherNotFoundException::new);
    }

    public List<Teacher> findAll() {
        return teacherJpaRepository.findAll();
    }

    public Teacher save(Teacher teacher) {
        return teacherJpaRepository.save(teacher);
    }

    public boolean existsById(Long id) {
        return teacherJpaRepository.existsById(id);
    }

}
