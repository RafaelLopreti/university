package com.lopreti.university.adapters.repositories.impl;

import com.lopreti.university.adapters.repositories.jpa.CourseJpaRepository;
import com.lopreti.university.domain.entities.Course;
import com.lopreti.university.domain.exception.CourseNotFoundException;
import com.lopreti.university.domain.ports.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepositoryImpl implements CourseRepository {

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    public Course findById(Long id) {
        return courseJpaRepository.findById(id).orElseThrow(CourseNotFoundException::new);
    }

    public List<Course> findAll() {
        return courseJpaRepository.findAll();
    }

    @Override
    public Course findByName(String courseName) {
        return courseJpaRepository.findByName(courseName).orElseThrow(CourseNotFoundException::new);
    }

    @Override
    public List<Course> findByPeriod(String period) {
        return courseJpaRepository.findByPeriod(period);
    }

    public Course save(Course course) {
        return courseJpaRepository.save(course);
    }

    public boolean existsById(Long id) {
        return courseJpaRepository.existsById(id);
    }

    public Optional<?> existsByName(String name) {
        return courseJpaRepository.existsByName(name);
    }

    public Optional<?> existsByPeriod(String period) {
        return courseJpaRepository.existsByPeriod(period);
    }
}
