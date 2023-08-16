package com.lopreti.university.adapters.db.teacher;

import com.lopreti.university.domain.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherJpaRepository extends JpaRepository<Teacher, Long> {

    @Query(value = "SELECT * FROM Teacher WHERE class = ?1", nativeQuery = true)
    List<Teacher> findByClass(String classCode);

}
