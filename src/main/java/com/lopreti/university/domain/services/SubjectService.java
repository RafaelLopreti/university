package com.lopreti.university.domain.services;

import com.lopreti.university.adapters.repositories.impl.SubjectsRepositoryImpl;
import com.lopreti.university.domain.dtos.ListRequestDto;
import com.lopreti.university.domain.entities.Subjects;
import com.lopreti.university.domain.entities.Teacher;
import com.lopreti.university.domain.exception.subject.SubjectAlreadyExistsException;
import com.lopreti.university.domain.exception.subject.SubjectNotFoundException;
import com.lopreti.university.domain.exception.teacher.TeacherNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SubjectService {

    private final SubjectsRepositoryImpl subjectsRepository;

    private final TeacherService teacherService;

    public SubjectService(SubjectsRepositoryImpl subjectsRepository, TeacherService teacherService) {
        this.subjectsRepository = subjectsRepository;
        this.teacherService = teacherService;
    }

    public Subjects findById(Long id) {
        return subjectsRepository.findById(id);
    }

    public Subjects findByName(String name) {
        Subjects subject = subjectsRepository.findByName(name);

        if (subject != null) {
            return subject;
        }

        throw new SubjectNotFoundException(name);
    }

    public List<Subjects> findAll(){
        return subjectsRepository.findAll();
    }

    public Subjects save(Subjects subjects) {
        if (subjectsRepository.findByName(subjects.getName()) != null) {
            throw new SubjectAlreadyExistsException();
        }
        return subjectsRepository.save(subjects);
    }

    public Subjects updateName(Long id, String name) {
        Subjects subject = findById(id);

        if (subjectsRepository.existsByName(name) == null) {
            subject.setName(name);
            return subjectsRepository.save(subject);
        }

        throw new SubjectNotFoundException(id);
    }

    public Subjects updateTeacherList(Long id, ListRequestDto requestList) {
        Subjects subject = findById(id);
        List<Long> peopleList = requestList.getTeacherList();

        if (subject == null) {
            throw new SubjectNotFoundException(id);
        }

        List<Teacher> updatedTeacherList = new ArrayList<>();
        for (Long personId : peopleList) {
            Teacher existingTeacher = teacherService.findById(personId);
            if (existingTeacher != null) {
                updatedTeacherList.add(existingTeacher);
            }
        }
        subject.setTeacherList(updatedTeacherList);

        return subjectsRepository.save(subject);
    }

    public boolean existsById(Long id) {
        return subjectsRepository.existsById(id);
    }

    public Subjects update(Long id, Subjects subjectBody) {
        Subjects subject = findById(id);
        List<Teacher> teacherList = new ArrayList<>();

        for (Teacher teacher : subjectBody.getTeacherList()) {
            Long teacherId = teacher.getId();
            Teacher teacherExist = teacherService.findById(teacherId);
            if (teacherExist != null) {
                teacherList.add(teacherExist);
            } else {
                throw new TeacherNotFoundException(teacherId);
            }
        }

        subject.setName(Objects.requireNonNullElse(subjectBody.getName(), subject.getName()));
        subject.setTeacherList(teacherList);

        return subjectsRepository.save(subject);
    }
}
