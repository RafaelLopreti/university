package com.lopreti.university.domain.services;

import com.lopreti.university.adapters.repositories.impl.PeopleRepositoryImpl;
import com.lopreti.university.domain.entities.Address;
import com.lopreti.university.domain.entities.People;
import com.lopreti.university.domain.entities.Users;
import com.lopreti.university.domain.exception.address.AddressNotFoundException;
import com.lopreti.university.domain.exception.address.AddressUserExistsException;
import com.lopreti.university.domain.exception.others.NoValidFieldException;
import com.lopreti.university.domain.exception.others.ValueCannotBeEmptyException;
import com.lopreti.university.domain.exception.people.PeopleAlreadyExistsException;
import com.lopreti.university.domain.exception.people.PeopleCategoryNotFoundException;
import com.lopreti.university.domain.exception.people.PeopleNotFoundException;
import com.lopreti.university.domain.exception.people.PeopleUserExistsException;
import com.lopreti.university.domain.exception.user.UserNotFoundException;
import com.lopreti.university.domain.valueObjects.PeopleCategory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PeopleService {

    private final PeopleRepositoryImpl peopleRepository;

    private final AddressService addressService;

    private final UserService userService;

    public PeopleService(PeopleRepositoryImpl peopleRepository, AddressService addressService, UserService userService) {
        this.peopleRepository = peopleRepository;
        this.addressService = addressService;
        this.userService = userService;
    }

    public People findById(Long id) {
        return peopleRepository.findById(id).orElseThrow(() -> new PeopleNotFoundException(id));
    }

    public List<People> findAll(){
        return peopleRepository.findAll();
    }

    public List<People> findByName(String peopleName) {
        return peopleRepository.findByName(peopleName).orElseThrow(() -> new PeopleNotFoundException(peopleName));
    }

    public People findByRegistry(String taxpayerRegistry) {
        People people = peopleRepository.findByRegistry(taxpayerRegistry);

        if (people != null) {
            return people;
        }

        throw new PeopleNotFoundException(taxpayerRegistry);
    }

    public People findByAddressId(Long addressId) {
        return peopleRepository.findByAddressId(addressId)
                .orElseThrow(() -> new PeopleNotFoundException(addressId, "addressId"));
    }

    public People findByUserId(Long userId) {
        return peopleRepository.findByUserId(userId)
                .orElseThrow(() -> new PeopleNotFoundException(userId, "userId"));
    }

    public List<People> findByCategory(String category) {
        String teacherCategory = PeopleCategory.TEACHER.name();
        String studentCategory = PeopleCategory.STUDENT.name();
        String visitantCategory = PeopleCategory.VISITANT.name();

        if (category.equalsIgnoreCase(teacherCategory)) {
            return peopleRepository.findByCategory(teacherCategory);
        } else if (category.equalsIgnoreCase(studentCategory)) {
            return peopleRepository.findByCategory(studentCategory);
        } else if (category.equalsIgnoreCase(visitantCategory)) {
            return peopleRepository.findByCategory(visitantCategory);
        }

        throw new PeopleCategoryNotFoundException(category);
    }

    public People save(People people) {
        Users user = people.getUser();

        if (user == null || userService.findById(user.getId()) == null) {
            throw new UserNotFoundException();
        }

        validatePeopleUser(user.getId());

        Address address = people.getAddress();
        if (address == null || addressService.findById(address.getId()) == null) {
            throw new AddressNotFoundException();
        }

        validateAddressUser(address.getId());

        if (peopleRepository.findByRegistry(people.getTaxpayerRegistry()) != null) {
            throw new PeopleAlreadyExistsException();
        }

        return peopleRepository.save(people);
    }

    public People update(Long id, String key, String value) {
        People people = findById(id);

        if (!value.isEmpty()) {
            switch (key) {
                case "name" -> people.setName(value);
                case "taxpayer" -> people.setTaxpayerRegistry(value);
                case "address" -> {
                    Long addressId = Long.parseLong(value);
                    validateAddressUser(addressId);
                    Address address = addressService.findById(Long.parseLong(value));
                    if (address != null) {
                        people.setAddress(address);
                    } else {
                        throw new AddressNotFoundException();
                    }
                }
                case "user" -> {
                    Long userId = Long.parseLong(value);
                    validatePeopleUser(userId);
                    Users user = userService.findById(Long.parseLong(value));
                    if (user != null) {
                        people.setUser(user);
                    } else {
                        throw new UserNotFoundException();
                    }
                }
                case "category" -> people.setCategory(PeopleCategory.valueOf(value));
                default -> throw new NoValidFieldException(key);
            }
        } else {
            throw new ValueCannotBeEmptyException();
        }

        return people;
    }

    public People update(Long id, People peopleBody) {
        People people = findById(id);

        people.setName(Objects.requireNonNullElse(peopleBody.getName(), people.getName()));
        people.setTaxpayerRegistry(Objects.requireNonNullElse(peopleBody.getTaxpayerRegistry(), people.getTaxpayerRegistry()));
        people.setAddress(Objects.requireNonNullElse(peopleBody.getAddress(), people.getAddress()));
        people.setUser(Objects.requireNonNullElse(peopleBody.getUser(), people.getUser()));
        people.setCategory(Objects.requireNonNullElse(peopleBody.getCategory(), people.getCategory()));

        return save(people);
    }

    private void validateAddressUser(Long addressId) {
        if (peopleRepository.findByAddressId(addressId).isPresent()) {
            throw new AddressUserExistsException(addressId);
        }
    }

    private void validatePeopleUser(Long userId) {
        if (peopleRepository.findByUserId(userId).isPresent()) {
            throw new PeopleUserExistsException(userId);
        }
    }

}
