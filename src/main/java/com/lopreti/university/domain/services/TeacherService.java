package com.lopreti.university.domain.services;

import com.lopreti.university.adapters.repositories.impl.TeacherRepositoryImpl;
import com.lopreti.university.domain.entities.Teacher;
import com.lopreti.university.domain.exception.ClassesNotFoundException;
import com.lopreti.university.domain.exception.TeacherAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TeacherService {

    private final TeacherRepositoryImpl teacherRepository;

    private final ClassesService classesService;

    public TeacherService(TeacherRepositoryImpl teacherRepository, ClassesService classesService) {
        this.teacherRepository = teacherRepository;
        this.classesService = classesService;
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

        if (classesService.existsByCode(classCode).isPresent()) {
            teacher.setClassCode(classCode);
            return teacherRepository.save(teacher);
        }

        throw new ClassesNotFoundException(classCode);
    }

    public Teacher update(Long id, Teacher teacherBody) {
        Teacher teacher = findById(id);
        String teacherClassCode = teacherBody.getClassCode();

        if (classesService.existsByCode(teacherClassCode).isPresent()) {
            teacher.setClassCode(teacherClassCode);
        } else {
            throw new ClassesNotFoundException(teacherClassCode);
        }
        teacher.setPeople(Objects.requireNonNullElse(teacherBody.getPeople(), teacher.getPeople()));

        return teacherRepository.save(teacher);

    }


    public boolean existsById(Long id) {
        return teacherRepository.existsById(id);
    }

}
