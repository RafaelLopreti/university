package com.lopreti.university.adapters.db.course;

import com.lopreti.university.domain.entities.Course;
import com.lopreti.university.domain.ports.repositories.CourseRepository;
import com.lopreti.university.domain.valueObjects.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepositoryImpl implements CourseRepository {

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Override
    public Course findByName(String courseName) {
        return courseJpaRepository.findByName(courseName);
    }

    @Override
    public List<Course> findByPeriod(Period period) {
        return courseJpaRepository.findByPeriod(period);
    }
}
