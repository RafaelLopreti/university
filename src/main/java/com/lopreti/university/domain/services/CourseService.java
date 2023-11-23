package com.lopreti.university.domain.services;

import com.lopreti.university.adapters.repositories.impl.CourseRepositoryImpl;
import com.lopreti.university.adapters.repositories.impl.SubjectsRepositoryImpl;
import com.lopreti.university.domain.entities.Course;
import com.lopreti.university.domain.entities.Subjects;
import com.lopreti.university.domain.exception.course.CourseAlreadyExistsException;
import com.lopreti.university.domain.exception.course.CourseNotFoundException;
import com.lopreti.university.domain.exception.course.CoursePeriodNotFoundException;
import com.lopreti.university.domain.exception.others.NoValidFieldException;
import com.lopreti.university.domain.exception.others.ValueCannotBeEmptyException;
import com.lopreti.university.domain.exception.subject.SubjectNotFoundException;
import com.lopreti.university.domain.valueObjects.Period;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepositoryImpl courseRepository;

    private final SubjectsRepositoryImpl subjectsRepository;

    public CourseService(CourseRepositoryImpl courseRepository, SubjectsRepositoryImpl subjectsRepository) {
        this.courseRepository = courseRepository;
        this.subjectsRepository = subjectsRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(Long id) {
        return courseRepository.findById(id);
    }

    public Course findByName(String name) {
        return courseRepository.findByName(name);
    }

    public List<Course> findByPeriod(String period) {
        return switch (period.toUpperCase()) {
            case "MORNING", "AFTERNOON", "NIGHT" -> {
                if (existsByPeriod(period).isEmpty()) {
                    throw new CourseNotFoundException();
                }
                yield courseRepository.findByPeriod(period);
            }
            default -> throw new CoursePeriodNotFoundException(period);
        };
    }

    public Course save(Course course) {
        if (existsByName(course.getName()).isEmpty()) {
            return courseRepository.save(course);
        }
        throw new CourseAlreadyExistsException(course.getName());
    }

    public Course update(Long id, String key, String value) {
        Course course = findById(id);

        if (!value.isEmpty()) {
            switch (key) {
                case "name" -> course.setName(value);
                case "period" -> course.setPeriod(toPeriod(value));
                default -> throw new NoValidFieldException(key);
            }
        } else {
            throw new ValueCannotBeEmptyException();
        }

        return courseRepository.save(course);
    }

    public Course update(Long id, Course courseBody) {
        Course course = findById(id);
        List<Subjects> subjectsList = new ArrayList<>();

        for (Subjects subject : courseBody.getSubjectsList()) {
            Long subjectId = subject.getId();
            Subjects subjectsOptional = subjectsRepository.findById(subjectId);
            if (subjectsOptional != null) {
                subjectsList.add(subjectsOptional);
            } else {
                throw new SubjectNotFoundException(subjectId);
            }
        }

        course.setName(Objects.requireNonNullElse(courseBody.getName(), course.getName()));
        course.setPeriod(Objects.requireNonNullElse(courseBody.getPeriod(), course.getPeriod()));
        course.setSubjectsList(subjectsList);

        return courseRepository.save(course);
    }

    public boolean existsById(Long id) {
        return courseRepository.existsById(id);
    }

    public Optional<?> existsByName(String name) {
        return courseRepository.existsByName(name);
    }

    public Optional<?> existsByPeriod(String period) {
        return courseRepository.existsByPeriod(period);
    }

    private Period toPeriod(String period) {
        return switch (period.toUpperCase()) {
            case "MORNING" -> Period.MORNING;
            case "AFTERNOON" -> Period.AFTERNOON;
            case "NIGHT" -> Period.NIGHT;
            default -> throw new NoValidFieldException(period);
        };
    }

}
