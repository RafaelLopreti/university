package com.lopreti.university.adapters.repositories.impl;

import com.lopreti.university.adapters.repositories.jpa.PeopleJpaRepository;
import com.lopreti.university.domain.entities.People;
import com.lopreti.university.domain.ports.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PeopleRepositoryImpl implements PeopleRepository {

    @Autowired
    private PeopleJpaRepository peopleJpaRepository;

    public Optional<People> findById(Long id) {
        return peopleJpaRepository.findById(id);
    }

    public List<People> findAll() {
        return peopleJpaRepository.findAll();
    }

    public People save(People people) {
        return peopleJpaRepository.save(people);
    }

    @Override
    public Optional<List<People>> findByName(String peopleName) {
        return peopleJpaRepository.findByName(peopleName);
    }

    @Override
    public People findByRegistry(String taxpayerRegistry) {
        return peopleJpaRepository.findByRegistry(taxpayerRegistry);
    }

    @Override
    public Optional<People> findByAddressId(Long addressId) {
        return peopleJpaRepository.findByAddressId(addressId);
    }

    @Override
    public Optional<People> findByUserId(Long userId) {
        return peopleJpaRepository.findByUserId(userId);
    }

    @Override
    public List<People> findByCategory(String category) {
        return peopleJpaRepository.findByCategory(category);
    }

}
