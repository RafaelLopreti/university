package com.lopreti.university.adapters.repositories.jpa;

import com.lopreti.university.domain.entities.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClassJpaRepository extends JpaRepository<Class, Long> {

    @Query(value = "SELECT * FROM Class WHERE code = ?1", nativeQuery = true)
    public Class findByCode(String classCode);

    @Query(value = "SELECT * FROM Class WHERE code = ?1", nativeQuery = true)
    public Optional<Class> existsByCode(String classCode);

}
