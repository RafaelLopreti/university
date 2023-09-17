package com.lopreti.university.domain.services;

import com.lopreti.university.adapters.repositories.impl.ClassRepositoryImpl;
import com.lopreti.university.adapters.repositories.impl.PeopleRepositoryImpl;
import com.lopreti.university.adapters.repositories.impl.SubjectsRepositoryImpl;
import com.lopreti.university.domain.entities.Class;
import com.lopreti.university.domain.entities.People;
import com.lopreti.university.domain.entities.Subjects;
import com.lopreti.university.domain.exception.ClassAlreadyExistsException;
import com.lopreti.university.domain.exception.ClassNotFoundException;
import com.lopreti.university.domain.exception.NoValidFieldException;
import com.lopreti.university.domain.exception.ValueCannotBeEmptyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassService {

    private final ClassRepositoryImpl classRepository;

    private final PeopleRepositoryImpl peopleRepository;

    private final SubjectsRepositoryImpl subjectsRepository;

    public ClassService(ClassRepositoryImpl classRepository, PeopleRepositoryImpl peopleRepository,
                        SubjectsRepositoryImpl subjectsRepository) {
        this.classRepository = classRepository;
        this.peopleRepository = peopleRepository;
        this.subjectsRepository = subjectsRepository;
    }

    public Class findById(Long id) {
        return classRepository.findById(id);
    }

    public List<Class> findAll(){
        return classRepository.findAll();
    }

    public Class findByCode(String classCode) {
        return classRepository.findByCode(classCode);
    }

    public Class save(Class classes) {
        if (!existsById(classes.getId())) {
            return classRepository.save(classes);
        }
        throw new ClassAlreadyExistsException();
    }

    public Class updateCode(Long id, String key, String value) {
        Class classes = findById(id);

        if (!value.isEmpty()) {
            if (key.equals("code")) {
                classes.setCode(value);
            } else {
                throw new NoValidFieldException(key);
            }
        } else {
            throw new ValueCannotBeEmptyException();
        }

        return classRepository.save(classes);
    }

    public Class updatePeopleList(String classCode, List<Long> newPeopleIds) {
        Class classes = findByCode(classCode);

        if (classes == null) {
            throw new ClassNotFoundException();
        }

        List<People> updatedPeopleList = new ArrayList<>();
        for (Long personId : newPeopleIds) {
            People existingPerson = peopleRepository.findById(personId);
            if (existingPerson != null) {
                updatedPeopleList.add(existingPerson);
            }
        }
        classes.setPeopleList(updatedPeopleList);

        return classRepository.save(classes);
    }

    public Class updateSubjectsList(String classCode, List<Long> newSubjectsIds) {
        Class classes = findByCode(classCode);

        if (classes == null) {
            throw new ClassNotFoundException();
        }

        List<Subjects> updatedSubjectsList = new ArrayList<>();
        for (Long subjectId : newSubjectsIds) {
            Subjects existingSubject = subjectsRepository.findById(subjectId);
            if (existingSubject != null) {
                updatedSubjectsList.add(existingSubject);
            }
        }
        classes.setSubjectsList(updatedSubjectsList);

        return classRepository.save(classes);
    }

    public boolean existsById(Long id) {
        return classRepository.existsById(id);
    }

}
