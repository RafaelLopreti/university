package com.lopreti.university.adapters.repositories.impl;

import com.lopreti.university.adapters.repositories.jpa.PeopleJpaRepository;
import com.lopreti.university.domain.entities.Class;
import com.lopreti.university.domain.entities.People;
import com.lopreti.university.domain.exception.ClassNotFoundException;
import com.lopreti.university.domain.exception.PeopleNotFoundException;
import com.lopreti.university.domain.ports.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PeopleRepositoryImpl implements PeopleRepository {

    @Autowired
    private PeopleJpaRepository peopleJpaRepository;

    public People findById(Long id) {
        return peopleJpaRepository.findById(id).orElseThrow(PeopleNotFoundException::new);
    }

    @Override
    public List<People> findByName(String peopleName) {
        return peopleJpaRepository.findByName(peopleName);
    }

    @Override
    public People findByRegistry(String taxpayerRegistry) {
        return peopleJpaRepository.findByRegistry(taxpayerRegistry);
    }

    @Override
    public List<People> findByAddressId(Long addressId) {
        return peopleJpaRepository.findByAddressId(addressId);
    }
}
