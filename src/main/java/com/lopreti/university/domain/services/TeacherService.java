package com.lopreti.university.domain.services;

import com.lopreti.university.adapters.repositories.impl.TeacherRepositoryImpl;
import com.lopreti.university.domain.entities.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepositoryImpl teacherRepository;

    public TeacherService(TeacherRepositoryImpl teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher findById(Long id) {
        return teacherRepository.findById(id);
    }

    public List<Teacher> findAll(){
        return teacherRepository.findAll();
    }

    public List<Teacher> findByClass(String classCode) {
        return teacherRepository.findByClass(classCode);
    }

    public Teacher save(Teacher teacher) {
        if (!existsById(teacher.getId())) {
            return teacherRepository.save(teacher);
        }
        return null; // TODO TEACHER EXCEPTION
    }

    public Teacher update(Long id, String classCode) {
        Teacher teacher = findById(id);
        teacher.setClassList(classCode); // TODO VALID EXIST CLASSCODE
        return save(teacher);
    }

    public boolean existsById(Long id) {
        return teacherRepository.existsById(id);
    }

}
