package com.lopreti.university.domain.ports.repositories;

import com.lopreti.university.domain.entities.People;

import java.util.List;
import java.util.Optional;

public interface PeopleRepository {

    Optional<List<People>> findByName(String peopleName);

    People findByRegistry(String taxpayerRegistry);

    Optional<People> findByAddressId(Long addressId);

    Optional<People> findByUserId(Long userId);

    List<People> findByCategory(String category);
}
