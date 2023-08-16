package com.lopreti.university.adapters.db.subjects;

import com.lopreti.university.domain.entities.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubjectsJpaRepository extends JpaRepository<Subjects, Long> {

    @Query(value = "SELECT * FROM Subjects WHERE name = ?1", nativeQuery = true)
    public Subjects findByName(String name);

}
