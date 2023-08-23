package com.lopreti.university.domain.services;

import com.lopreti.university.adapters.repositories.impl.AddressRepositoryImpl;
import com.lopreti.university.domain.entities.Address;
import com.lopreti.university.domain.entities.Teacher;
import com.lopreti.university.domain.exception.ClassNotFoundException;
import com.lopreti.university.domain.exception.TeacherAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepositoryImpl addressRepository;

    public AddressService(AddressRepositoryImpl addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address findById(Long id) {
        return addressRepository.findById(id);
    }

    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    public List<Address> findByStreet(String street) {
        return addressRepository.findByStreet(street);
    }

    public List<Address> findByNumber(String number) {
        return addressRepository.findByNumber(number);
    }

    public List<Address> findByCity(String city) {
        return addressRepository.findByCity(city);
    }

    public List<Address> findByNeighborhood(String neighborhood) {
        return addressRepository.findByNeighborhood(neighborhood);
    }

    public List<Address> findByZipCode(String zipCode) {
        return addressRepository.findByZipCode(zipCode);
    }

    public List<Address> findByCountry(String country) {
        return addressRepository.findByCountry(country);
    }

    public Address save(Address address) {
        if (!existsById(address.getId())) {
            return addressRepository.save(address);
        }
        throw new TeacherAlreadyExistsException();
    }

    public Address update(Long id, String classCode) {
        Address address = findById(id);
        return addressRepository.save(address);
    }

    public boolean existsById(Long id) {
        return addressRepository.existsById(id);
    }

}
