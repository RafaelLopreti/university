package com.lopreti.university.adapters.controllers;

import com.lopreti.university.domain.dtos.ListResponseDto;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/university")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/addresses")
    public ResponseEntity<ListResponseDto> getAll() {
        List<Address> addresses = this.addressService.findAll();

        for (Address address : addresses) {
            address.add(linkTo(methodOn(AddressController.class).getById(address.getId())).withRel("get-address-by-id"));
        }

        ListResponseDto response = new ListResponseDto();
        response.setObjects(addresses);
        response.setSelfLink(linkTo(methodOn(AddressController.class).getAll()).withSelfRel());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity<Address> getById(@PathVariable Long id) {
        Address address = this.addressService.findById(id);

        address.add(linkTo(methodOn(AddressController.class).getById(address.getId())).withSelfRel());
        address.add(linkTo(methodOn(AddressController.class).getAll()).withRel("get-all-addresses"));

        return ResponseEntity.ok(address);
    }

    @GetMapping("/addresses-by")
    public ListResponseDto getByParameters(
            @RequestParam(name = "street", required = false) String street,
            @RequestParam(name = "number", required = false) String number,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "neighborhood", required = false) String neighborhood,
            @RequestParam(name = "zipCode", required = false) String zipCode,
            @RequestParam(name = "country", required = false) String country,
            @RequestParam(name = "user", required = false) Long userId) {

        List<Address> addresses = null;

        if (street != null) {
            addresses = addressService.findByStreet(street);
        } else if (number != null) {
            addresses = addressService.findByNumber(number);
        } else if (city != null) {
            addresses = addressService.findByCity(city);
        } else if (neighborhood != null) {
            addresses = addressService.findByNeighborhood(neighborhood);
        } else if (zipCode != null) {
            addresses = addressService.findByZipCode(zipCode);
        } else if (country != null) {
            addresses = addressService.findByCountry(country);
        } else if (userId != null) {
            addresses = addressService.findByUserId(userId);
        }

        if (addresses != null) {
            for (Address address : addresses) {
                address.add(linkTo(methodOn(AddressController.class).getById(address.getId())).withSelfRel());
            }
            ListResponseDto response = new ListResponseDto();
            response.setObjects(addresses);
            response.setSelfLink(linkTo(methodOn(AddressController.class).getAll()).withRel("get-all-addresses"));
            return response;
        } else {
            throw new AddressNotFoundException();
        }
    }

    @PostMapping("/addresses")
    public ResponseEntity<?> save(@Valid @RequestBody Address addressSave) {
        Address address = this.addressService.save(addressSave);

        address.add(linkTo(methodOn(AddressController.class).getById(addressSave.getId())).withSelfRel());
        address.add(linkTo(methodOn(AddressController.class).getAll()).withRel("get-all-addresses"));

        return new ResponseEntity<>(address, HttpStatus.CREATED);
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
            Address address = this.addressService.update(id, keys.get(0), values.get(0));

            address.add(linkTo(methodOn(AddressController.class).getById(address.getId())).withSelfRel());
            address.add(linkTo(methodOn(AddressController.class).getAll()).withRel("get-all-addresses"));

            return new ResponseEntity<>(address, HttpStatus.OK);
        }
    }

    @PutMapping("/addresses/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable("id") Long id, @RequestBody Address address) {
        Address addresses = this.addressService.update(id, address);

        addresses.add(linkTo(methodOn(AddressController.class).getById(id)).withRel("get-address-by-id"));
        addresses.add(linkTo(methodOn(AddressController.class).getAll()).withRel("get-all-addresses"));

        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

}
