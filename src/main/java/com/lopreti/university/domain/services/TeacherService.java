package com.lopreti.university.domain.services;

import com.lopreti.university.adapters.repositories.impl.ClassRepositoryImpl;
import com.lopreti.university.adapters.repositories.impl.TeacherRepositoryImpl;
import com.lopreti.university.domain.entities.Teacher;
import com.lopreti.university.domain.exception.ClassNotFoundException;
import com.lopreti.university.domain.exception.TeacherAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepositoryImpl teacherRepository;

    private final ClassRepositoryImpl classRepository;

    public TeacherService(TeacherRepositoryImpl teacherRepository, ClassRepositoryImpl classRepository) {
        this.teacherRepository = teacherRepository;
        this.classRepository = classRepository;
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
        throw new TeacherAlreadyExistsException();
    }

    public Teacher update(Long id, String classCode) {
        Teacher teacher = findById(id);

        if (classRepository.existsByCode(classCode).isPresent()) {
            teacher.setClassList(classCode);
            return teacherRepository.save(teacher);
        } // TODO CALL CLASS SERVICE

        throw new ClassNotFoundException();
    }

    public boolean existsById(Long id) {
        return teacherRepository.existsById(id);
    }

}
