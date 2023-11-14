package com.lopreti.university.domain.services;

import com.lopreti.university.adapters.repositories.impl.AddressRepositoryImpl;
import com.lopreti.university.domain.entities.Address;
import com.lopreti.university.domain.entities.Users;
import com.lopreti.university.domain.exception.NoValidFieldException;
import com.lopreti.university.domain.exception.UserAddressExistsException;
import com.lopreti.university.domain.exception.UserIsRequiredException;
import com.lopreti.university.domain.exception.ValueCannotBeEmptyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AddressService {

    private final AddressRepositoryImpl addressRepository;

    private final UserService userService;

    public AddressService(AddressRepositoryImpl addressRepository, UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
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

    public List<Address> findByUserId(Long id) {
        return addressRepository.findByUserId(id);
    }

    public Address save(Address address) {
        Long userId = address.getUser() != null ? address.getUser().getId() : null;
        if (userId != null) {
            if (isAddressListEmptyForUser(userId)) {
                return addressRepository.save(address);
            }
            throw new UserAddressExistsException(userId);
        } else {
            throw new UserIsRequiredException();
        }
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
                case "user" -> {
                    Users user = userService.findById(Long.parseLong(value));
                    if (isAddressListEmptyForUser(user.getId())) {
                        address.setUser(user);
                    } else {
                        throw new UserAddressExistsException(user.getId());
                    }
                }
                default -> throw new NoValidFieldException(key);
            }
        } else {
            throw new ValueCannotBeEmptyException();
        }

        return addressRepository.save(address);
    }

    public Address update(Long id, Address addressBody) {
        Address address = findById(id);

        address.setStreet(Objects.requireNonNullElse(addressBody.getStreet(), address.getStreet()));
        address.setNumber(Objects.requireNonNullElse(addressBody.getNumber(), address.getNumber()));
        address.setCity(Objects.requireNonNullElse(addressBody.getCity(), address.getCity()));
        address.setNeighborhood(Objects.requireNonNullElse(addressBody.getNeighborhood(), address.getNeighborhood()));
        address.setZipCode(Objects.requireNonNullElse(addressBody.getZipCode(), address.getZipCode()));
        address.setCountry(Objects.requireNonNullElse(addressBody.getCountry(), address.getCountry()));

        if (addressBody.getUser() != null) {
            if (isAddressListEmptyForUser(addressBody.getUser().getId())) {
                address.setUser(addressBody.getUser());
            } else {
                throw new UserAddressExistsException(addressBody.getUser().getId());
            }
        }

        return addressRepository.save(address);
    }

    public boolean existsById(Long id) {
        return addressRepository.existsById(id);
    }

    public boolean isAddressListEmptyForUser(Long userId) {
        Users user = userService.findById(userId);
        if (user != null) {
            return findByUserId(userId).isEmpty();
        } else {
            return false;
        }
    }

}
