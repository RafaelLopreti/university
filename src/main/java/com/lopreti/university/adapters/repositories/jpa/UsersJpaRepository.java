package com.lopreti.university.adapters.repositories.jpa;

import com.lopreti.university.domain.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersJpaRepository extends JpaRepository<Users, Long> {

    @Query(value = "SELECT * FROM Users WHERE LOWER(email) = LOWER(?1)", nativeQuery = true)
    Optional<Users> findByEmail(String email);

    @Query(value = "SELECT * FROM Users WHERE email = ?1 AND password = ?2", nativeQuery = true)
    Optional<Users> findToLogin(String email, String password);

    @Query(value = "SELECT * FROM Users WHERE email = ?1", nativeQuery = true)
    Optional<Users> existsByEmail(String email);
}
