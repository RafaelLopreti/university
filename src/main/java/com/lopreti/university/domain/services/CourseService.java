package com.lopreti.university.domain.services;

import com.lopreti.university.adapters.repositories.impl.CourseRepositoryImpl;
import com.lopreti.university.domain.entities.Course;
import com.lopreti.university.domain.exception.*;
import com.lopreti.university.domain.valueObjects.Period;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepositoryImpl courseRepository;

    public CourseService(CourseRepositoryImpl courseRepository) {
        this.courseRepository = courseRepository;
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
            default -> throw new PeriodNotFoundException(period);
        };
    }

    public Course save(Course course) {
        if (existsByName(course.getName()).isEmpty() && !existsById(course.getId())) {
            return courseRepository.save(course);
        }
        throw new CourseAlreadyExistsException();
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
