package com.lopreti.university.adapters.db.user;

import com.lopreti.university.domain.entities.User;
import com.lopreti.university.domain.valueObjects.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM User WHERE email = ?1", nativeQuery = true)
    public User findByEmail(Email email);

}
