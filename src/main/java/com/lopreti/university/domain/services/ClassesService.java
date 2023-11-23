package com.lopreti.university.domain.services;

import com.lopreti.university.adapters.repositories.impl.ClassRepositoryImpl;
import com.lopreti.university.adapters.repositories.impl.PeopleRepositoryImpl;
import com.lopreti.university.adapters.repositories.impl.SubjectsRepositoryImpl;
import com.lopreti.university.domain.dtos.ListRequestDto;
import com.lopreti.university.domain.entities.Classes;
import com.lopreti.university.domain.entities.People;
import com.lopreti.university.domain.entities.Subjects;
import com.lopreti.university.domain.exception.classes.ClassAlreadyExistsException;
import com.lopreti.university.domain.exception.classes.ClassNotFoundException;
import com.lopreti.university.domain.exception.others.NoValidFieldException;
import com.lopreti.university.domain.exception.others.ValueCannotBeEmptyException;
import com.lopreti.university.domain.exception.people.PeopleNotFoundException;
import com.lopreti.university.domain.exception.subject.SubjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        throw new ClassNotFoundException(classCode);
    }

    public Classes save(Classes classes) {
        if (classRepository.findByCode(classes.getCode()) == null) {
            return classRepository.save(classes);
        }
        throw new ClassAlreadyExistsException(classes.getCode());
    }

    public Classes updateCode(String code, String key, String value) {
        Classes classes = findByCode(code);

        if (!value.isEmpty()) {
            if (key.equals("code")) {
                if (classRepository.findByCode(value) == null) {
                    classes.setCode(value);
                } else {
                    throw new ClassAlreadyExistsException(value);
                }
            } else {
                throw new NoValidFieldException(key);
            }
        } else {
            throw new ValueCannotBeEmptyException();
        }

        return classRepository.save(classes);
    }

    public Classes updatePeopleList(String classCode, ListRequestDto requestList) {
        Classes classes = findByCode(classCode);
        List<Long> peopleList = requestList.getPeopleList();

        if (classes == null) {
            throw new ClassNotFoundException(classCode);
        }

        List<People> oldList = new ArrayList<>(classes.getPeopleList());
        List<People> updatedPeopleList = new ArrayList<>(oldList);

        for (Long personId : peopleList) {
            Optional<People> existingPerson = peopleRepository.findById(personId);
            if (existingPerson.isPresent() && !oldList.contains(existingPerson.get())) {
                updatedPeopleList.add(existingPerson.get());
            }
        }

        classes.setPeopleList(updatedPeopleList);

        return classRepository.save(classes);
    }

    public Classes updateSubjectsList(String classCode, ListRequestDto requestList) {
        Classes classes = findByCode(classCode);
        List<Long> subjectList = requestList.getSubjectList();

        if (classes == null) {
            throw new ClassNotFoundException(classCode);
        }

        List<Subjects> oldList = new ArrayList<>(classes.getSubjectsList());
        List<Subjects> updatedSubjectsList = new ArrayList<>(oldList);

        for (Long subjectId : subjectList) {
            Subjects existingSubject = subjectsRepository.findById(subjectId);
            if (existingSubject != null && !oldList.contains(existingSubject)) {
                updatedSubjectsList.add(existingSubject);
            }
        }
        classes.setSubjectsList(updatedSubjectsList);

        return classRepository.save(classes);
    }

    public Classes update(Long id, Classes classesBody) {
        Classes classes = findById(id);
        List<People> peopleList = new ArrayList<>();
        List<Subjects> subjectsList = new ArrayList<>();

        for (People person : classesBody.getPeopleList()) {
            Long personId = person.getId();
            Optional<People> personOptional = peopleRepository.findById(personId);
            if (personOptional.isPresent()) {
                peopleList.add(person);
            } else {
                throw new PeopleNotFoundException(personId);
            }
        }

        for (Subjects subject : classesBody.getSubjectsList()) {
            Long subjectId = subject.getId();
            Subjects subjectsOptional = subjectsRepository.findById(subjectId);
            if (subjectsOptional != null) {
                subjectsList.add(subjectsOptional);
            } else {
                throw new SubjectNotFoundException(subjectId);
            }
        }

        classes.setCode(Objects.requireNonNullElse(classesBody.getCode(), classes.getCode()));
        classes.setPeopleList(peopleList);
        classes.setSubjectsList(subjectsList);

        return classRepository.save(classes);
    }

    public boolean existsById(Long id) {
        return classRepository.existsById(id);
    }

    public Optional<Classes> existsByCode(String classCode) {
        return classRepository.existsByCode(classCode);
    }
}
