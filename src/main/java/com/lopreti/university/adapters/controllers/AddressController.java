package com.lopreti.university.adapters.controllers;

import com.lopreti.university.domain.entities.Address;
import com.lopreti.university.domain.exception.AddressNotFoundException;
import com.lopreti.university.domain.exception.MoreThanOneUpdateException;
import com.lopreti.university.domain.exception.WithoutFieldUpdateException;
import com.lopreti.university.domain.services.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/v1/university")
public class AddressController {

    // TODO IMPLEMENT HYPERMIDIA AND HANDLERS

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/addresses")
    public List<Address> getAll() {
        return this.addressService.findAll();
    }

    @GetMapping("/addresses/{id}")
    public Address getById(@PathVariable Long id) {
        return this.addressService.findById(id);
    }

    @GetMapping("/addresses-by")
    public List<Address> getByParameters(
            @RequestParam(name = "street", required = false) String street,
            @RequestParam(name = "number", required = false) String number,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "neighborhood", required = false) String neighborhood,
            @RequestParam(name = "zipCode", required = false) String zipCode,
            @RequestParam(name = "country", required = false) String country) {

        if (street != null) {
            return addressService.findByStreet(street);
        } else if (number != null) {
            return addressService.findByNumber(number);
        } else if (city != null) {
            return addressService.findByCity(city);
        } else if (neighborhood != null) {
            return addressService.findByNeighborhood(neighborhood);
        } else if (zipCode != null) {
            return addressService.findByZipCode(zipCode);
        } else if (country != null) {
            return addressService.findByCountry(country);
        }

        throw new AddressNotFoundException();
    }

    @PostMapping("/addresses")
    public ResponseEntity<?> save(@Valid @RequestBody Address address) {
        this.addressService.save(address);
        return new ResponseEntity<>("Address created.", HttpStatus.CREATED);
    }

    @PatchMapping("/addresses/{id}")
    public ResponseEntity<?> updateAddress(
            @PathVariable("id") Long id,
            @RequestParam Map<String, String> allParams) {

        AtomicInteger updateCount = new AtomicInteger();
        List<String> keys = new ArrayList<>(allParams.keySet());
        List<String> values = new ArrayList<>(allParams.values());

        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i) != null && values.get(i) != null) {
                updateCount.getAndIncrement();
            }
        }

        if (updateCount.get() == 0) {
            throw new WithoutFieldUpdateException();
        } else if (updateCount.get() > 1) {
            throw new MoreThanOneUpdateException();
        } else {
            addressService.update(id, keys.get(0), values.get(0));
            return new ResponseEntity<>("Address updated.", HttpStatus.OK);
        }
    }

}
