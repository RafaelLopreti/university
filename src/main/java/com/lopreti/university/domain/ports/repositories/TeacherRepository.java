package com.lopreti.university.domain.ports.repositories;

import com.lopreti.university.domain.entities.Teacher;

import java.util.List;

public interface TeacherRepository {

    public List<Teacher> findByClass(String classCode);

}
