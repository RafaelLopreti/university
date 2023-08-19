package com.lopreti.university.domain.services;

import com.lopreti.university.adapters.repositories.impl.StudentRepositoryImpl;
import com.lopreti.university.domain.entities.Student;
import com.lopreti.university.domain.exception.StudentAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepositoryImpl  studentRepository;

    public StudentService(StudentRepositoryImpl studentRepository) {
        this.studentRepository = studentRepository;
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
        student.setClassCode(classCode); // TODO VALID EXIST CLASSCODE
        return save(student);
    }

    public boolean existsById(Long id) {
        return studentRepository.existsById(id);
    }

}
