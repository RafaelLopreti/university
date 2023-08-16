package com.lopreti.university.domain.ports.repositories;

import com.lopreti.university.domain.entities.Student;

import java.util.List;

public interface StudentRepository {

    public List<Student> findByClass(String classCode);

}
