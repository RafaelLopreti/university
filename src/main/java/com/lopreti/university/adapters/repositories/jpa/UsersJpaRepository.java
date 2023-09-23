package com.lopreti.university.adapters.repositories.jpa;

import com.lopreti.university.domain.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersJpaRepository extends JpaRepository<Users, Long> {

    @Query(value = "SELECT * FROM Users WHERE email = ?1", nativeQuery = true)
    Users findByEmail(String email);

    @Query(value = "SELECT email FROM Users WHERE email = ?1", nativeQuery = true)
    Optional<String> existsByEmail(String email);
}
