package com.lopreti.university.domain.ports.repositories;

import com.lopreti.university.domain.entities.People;

import java.util.List;

public interface PeopleRepository {

    public List<People> findByName(String peopleName);

    public People findByRegistry(String taxpayerRegistry);

    public List<People> findByAddressId(Long addressId);

}
