package com.lopreti.university.domain.services;

import com.lopreti.university.adapters.repositories.impl.ClassRepositoryImpl;
import com.lopreti.university.adapters.repositories.impl.PeopleRepositoryImpl;
import com.lopreti.university.adapters.repositories.impl.SubjectsRepositoryImpl;
import com.lopreti.university.domain.entities.Classes;
import com.lopreti.university.domain.entities.People;
import com.lopreti.university.domain.entities.Subjects;
import com.lopreti.university.domain.exception.ClassAlreadyExistsException;
import com.lopreti.university.domain.exception.ClassesNotFoundException;
import com.lopreti.university.domain.exception.NoValidFieldException;
import com.lopreti.university.domain.exception.ValueCannotBeEmptyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassesService {

    private final ClassRepositoryImpl classRepository;

    private final PeopleRepositoryImpl peopleRepository;

    private final SubjectsRepositoryImpl subjectsRepository;

    public ClassesService(ClassRepositoryImpl classRepository, PeopleRepositoryImpl peopleRepository,
                          SubjectsRepositoryImpl subjectsRepository) {
        this.classRepository = classRepository;
        this.peopleRepository = peopleRepository;
        this.subjectsRepository = subjectsRepository;
    }

    public Classes findById(Long id) {
        return classRepository.findById(id);
    }

    public List<Classes> findAll(){
        return classRepository.findAll();
    }

    public Classes findByCode(String classCode) {
        if (classRepository.existsByCode(classCode).isPresent()) {
            return classRepository.findByCode(classCode);
        }
        throw new ClassesNotFoundException();
    }

    public Classes save(Classes classes) {
        if (!existsById(classes.getId())) {
            return classRepository.save(classes);
        }
        throw new ClassAlreadyExistsException();
    }

    public Classes updateCode(Long id, String key, String value) {
        Classes classes = findById(id);

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

    public Classes updatePeopleList(String classCode, List<Long> newPeopleIds) {
        Classes classes = findByCode(classCode);

        if (classes == null) {
            throw new ClassesNotFoundException();
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

    public Classes updateSubjectsList(String classCode, List<Long> newSubjectsIds) {
        Classes classes = findByCode(classCode);

        if (classes == null) {
            throw new ClassesNotFoundException();
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
