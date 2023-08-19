package com.lopreti.university.adapters.repositories.jpa;

import com.lopreti.university.domain.entities.Users;
import com.lopreti.university.domain.valueObjects.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersJpaRepository extends JpaRepository<Users, Long> {

    @Query(value = "SELECT * FROM User WHERE email = ?1", nativeQuery = true)
    public Users findByEmail(Email email);

}
