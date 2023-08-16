package com.lopreti.university.adapters.db.address;

import com.lopreti.university.domain.entities.Address;
import com.lopreti.university.domain.ports.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressRepositoryImpl implements AddressRepository {

    @Autowired
    private AddressJpaRepository addressJpaRepository;

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

}