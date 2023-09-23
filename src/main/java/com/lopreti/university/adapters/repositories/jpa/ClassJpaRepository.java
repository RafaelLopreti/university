package com.lopreti.university.adapters.repositories.jpa;

import com.lopreti.university.domain.entities.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClassJpaRepository extends JpaRepository<Classes, Long> {

    @Query(value = "SELECT * FROM Classes WHERE code = ?1", nativeQuery = true)
    public Classes findByCode(String classCode);

    @Query(value = "SELECT * FROM Classes WHERE code = ?1", nativeQuery = true)
    public Optional<Classes> existsByCode(String classCode);

}
