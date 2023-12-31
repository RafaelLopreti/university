package com.lopreti.university.adapters.repositories.jpa;

import com.lopreti.university.domain.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseJpaRepository extends JpaRepository<Course, Long> {

    @Query(value = "SELECT * FROM Course WHERE name = ?1", nativeQuery = true)
    Optional<Course> findByName(String courseName);

    @Query(value = "SELECT * FROM Course WHERE period = ?1", nativeQuery = true)
    List<Course> findByPeriod(String period);

    @Query(value = "SELECT * FROM Course WHERE name = ?1", nativeQuery = true)
    Optional<?> existsByName(String courseName);

    @Query(value = "SELECT * FROM Course WHERE period = ?1", nativeQuery = true)
    Optional<?> existsByPeriod(String period);

}
