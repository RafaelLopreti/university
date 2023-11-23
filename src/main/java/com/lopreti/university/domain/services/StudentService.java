package com.lopreti.university.domain.services;

import com.lopreti.university.adapters.repositories.impl.ClassRepositoryImpl;
import com.lopreti.university.adapters.repositories.impl.StudentRepositoryImpl;
import com.lopreti.university.domain.entities.Student;
import com.lopreti.university.domain.exception.classes.ClassNotFoundException;
import com.lopreti.university.domain.exception.student.StudentAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentService {

    private final StudentRepositoryImpl  studentRepository;

    private final ClassRepositoryImpl classRepository;

    public StudentService(StudentRepositoryImpl studentRepository, ClassRepositoryImpl classRepository) {
        this.studentRepository = studentRepository;
        this.classRepository = classRepository;
    }

    public Student findById(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public List<Student> findByClass(String classCode) {
        return studentRepository.findByClass(classCode);
    }

    public Student save(Student student) {
        if (!existsById(student.getId())) {
            return studentRepository.save(student);
        }
        throw new StudentAlreadyExistsException();
    }

    public Student update(Long id, String classCode) {
        Student student = findById(id);

        if (classRepository.existsByCode(classCode).isPresent()) {
            student.setClassCode(classCode);
            return studentRepository.save(student);
        }

        throw new ClassNotFoundException(classCode);
    }

    public Student update(Long id, Student studentBody) {
        Student student = findById(id);

        if (classRepository.existsByCode(studentBody.getClassCode()).isPresent()) {
            student.setClassCode(studentBody.getClassCode());
        } else {
            throw new ClassNotFoundException(studentBody.getClassCode());
        }
        student.setPeople(Objects.requireNonNullElse(studentBody.getPeople(), student.getPeople()));

        return studentRepository.save(student);
    }

    public boolean existsById(Long id) {
        return studentRepository.existsById(id);
    }

}
