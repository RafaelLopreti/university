package com.lopreti.university.adapters.repositories.jpa;

import com.lopreti.university.domain.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentJpaRepository extends JpaRepository<Student, Long> {

    @Query(value = "SELECT * FROM Student WHERE class_code = ?1", nativeQuery = true)
    List<Student> findByClass(String classCode);

}
