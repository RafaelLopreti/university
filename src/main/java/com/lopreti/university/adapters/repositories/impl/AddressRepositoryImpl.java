package com.lopreti.university.adapters.repositories.impl;

import com.lopreti.university.adapters.repositories.jpa.AddressJpaRepository;
import com.lopreti.university.domain.entities.Address;
import com.lopreti.university.domain.entities.Student;
import com.lopreti.university.domain.entities.Teacher;
import com.lopreti.university.domain.exception.AddressNotFoundException;
import com.lopreti.university.domain.exception.StudentNotFoundException;
import com.lopreti.university.domain.ports.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AddressRepositoryImpl implements AddressRepository {

    @Autowired
    private AddressJpaRepository addressJpaRepository;

    public Address findById(Long id) {
        return addressJpaRepository.findById(id).orElseThrow(AddressNotFoundException::new);
    }

    public List<Address> findAll() {
        return addressJpaRepository.findAll();
    }

    @Override
    public List<Address> findByStreet(String street) {
        return addressJpaRepository.findByStreet(street);
    }

    @Override
    public List<Address> findByNumber(String number) {
        return addressJpaRepository.findByNumber(number);
    }

    @Override
    public List<Address> findByCity(String city) {
        return addressJpaRepository.findByCity(city);
    }

    @Override
    public List<Address> findByNeighborhood(String neighborhood) {
        return addressJpaRepository.findByNeighborhood(neighborhood);
    }

    @Override
    public List<Address> findByZipCode(String zipCode) {
        return addressJpaRepository.findByZipCode(zipCode);
    }

    @Override
    public List<Address> findByCountry(String country) {
        return addressJpaRepository.findByCountry(country);
    }

    public Address save(Address address) {
        return addressJpaRepository.save(address);
    }

    public boolean existsById(Long id) {
        return addressJpaRepository.existsById(id);
    }

}
