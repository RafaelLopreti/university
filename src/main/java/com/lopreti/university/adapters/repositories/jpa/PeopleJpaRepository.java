package com.lopreti.university.adapters.repositories.jpa;

import com.lopreti.university.domain.entities.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PeopleJpaRepository extends JpaRepository<People, Long> {

    @Query(value = "SELECT * FROM People WHERE name LIKE %?1%", nativeQuery = true)
    Optional<List<People>> findByName(String peopleName);

    @Query(value = "SELECT * FROM People WHERE taxpayer_registry = ?1", nativeQuery = true)
    People findByRegistry(String taxpayerRegistry);

    @Query(value = "SELECT * FROM People WHERE address_id = ?1", nativeQuery = true)
    Optional<People> findByAddressId(Long addressId);

    @Query(value = "SELECT * FROM People WHERE category = ?1", nativeQuery = true)
    List<People> findByCategory(String category);

    @Query(value = "SELECT * FROM People WHERE user_id = ?1", nativeQuery = true)
    Optional<People> findByUserId(Long userId);
}
