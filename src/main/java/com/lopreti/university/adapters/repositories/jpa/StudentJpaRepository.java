package com.lopreti.university.adapters.repositories.jpa;

import com.lopreti.university.domain.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentJpaRepository extends JpaRepository<Student, Long> {

    @Query(value = "SELECT * FROM Student WHERE class_code = ?1", nativeQuery = true)
    List<Student> findByClass(String classCode);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM Student WHERE class_code = ?1 AND people_id = ?2", nativeQuery = true)
    boolean findByClassAndPeopleId(String classCode, Long peopleId);

}
