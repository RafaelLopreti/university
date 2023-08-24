package com.lopreti.university.domain.services;

import com.lopreti.university.adapters.repositories.impl.AddressRepositoryImpl;
import com.lopreti.university.domain.entities.Address;
import com.lopreti.university.domain.exception.NoValidFieldUpdateException;
import com.lopreti.university.domain.exception.TeacherAlreadyExistsException;
import com.lopreti.university.domain.exception.ValueCannotBeEmptyException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Address update(Long id, String key, String value) {
        Address address = findById(id);

        if (!value.isEmpty()) {
            switch (key) {
                case "street" -> address.setStreet(value);
                case "number" -> address.setNumber(value);
                case "city" -> address.setCity(value);
                case "neighborhood" -> address.setNeighborhood(value);
                case "zipCode" -> address.setZipCode(value);
                case "country" -> address.setCountry(value);
                default -> throw new NoValidFieldUpdateException(key);
            }
        } else {
            throw new ValueCannotBeEmptyException();
        }

        return addressRepository.save(address);
    }

    public boolean existsById(Long id) {
        return addressRepository.existsById(id);
    }

}
