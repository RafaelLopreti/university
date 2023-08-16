package com.lopreti.university.adapters.db.student;

import com.lopreti.university.domain.entities.Student;
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

}