package com.lopreti.university.domain.ports.repositories;

import com.lopreti.university.domain.entities.Course;

import java.util.List;

public interface CourseRepository {

    public Course findByName(String courseName);

    public List<Course> findByPeriod(String period);

}
