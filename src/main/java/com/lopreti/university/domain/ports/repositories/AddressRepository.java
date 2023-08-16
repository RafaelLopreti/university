package com.lopreti.university.domain.ports.repositories;

import com.lopreti.university.domain.entities.Address;

import java.util.List;

public interface AddressRepository {

    public List<Address> findByStreet(String street);

    public List<Address> findByNumber(String number);

    public List<Address> findByCity(String city);

    public List<Address> findByNeighborhood(String neighborhood);

    public List<Address> findByZipCode(String zipCode);

    public List<Address> findByCountry(String country);

}
