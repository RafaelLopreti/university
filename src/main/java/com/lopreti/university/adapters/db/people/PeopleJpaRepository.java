package com.lopreti.university.adapters.db.people;

import com.lopreti.university.domain.entities.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeopleJpaRepository extends JpaRepository<People, Long> {

    @Query(value = "SELECT * FROM People WHERE name = ?1", nativeQuery = true)
    List<People> findByName(String peopleName);

    @Query(value = "SELECT * FROM People WHERE taxpayerRegistry = ?1", nativeQuery = true)
    People findByRegistry(String taxpayerRegistry);

    @Query(value = "SELECT * FROM People WHERE address = ?1", nativeQuery = true)
    List<People> findByAddressId(Long addressId);

}
