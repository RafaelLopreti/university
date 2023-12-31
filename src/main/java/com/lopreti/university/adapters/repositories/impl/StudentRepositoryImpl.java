package com.lopreti.university.adapters.repositories.impl;

import com.lopreti.university.adapters.repositories.jpa.StudentJpaRepository;
import com.lopreti.university.domain.entities.Student;
import com.lopreti.university.domain.exception.student.StudentNotFoundException;
import com.lopreti.university.domain.ports.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    @Autowired
    private StudentJpaRepository studentJpaRepository;

    @Override
    public List<Student> findByClass(String classCode) {
        return studentJpaRepository.findByClass(classCode);
    }

    public List<Student> findAll() {
        return studentJpaRepository.findAll();
    }

    public Student findById(Long id) {
        return studentJpaRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    public Student save(Student student) {
        return studentJpaRepository.save(student);
    }

    public boolean existsById(Long id) {
        return studentJpaRepository.existsById(id);
    }

}
