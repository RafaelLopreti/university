package com.lopreti.university.adapters.controllers;

import com.lopreti.university.domain.dtos.ListResponseDto;
import com.lopreti.university.domain.entities.People;
import com.lopreti.university.domain.exception.others.MoreThanOneUpdateException;
import com.lopreti.university.domain.exception.others.WithoutFieldUpdateException;
import com.lopreti.university.domain.services.PeopleService;
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
public class PeopleController {
    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/peoples")
    public ResponseEntity<?> getAll() {
        List<People> peoples = this.peopleService.findAll();

        for (People people : peoples) {
            people.add(linkTo(methodOn(PeopleController.class).getById(people.getId()))
                    .withRel("get-people-by-id"));
            people.add(linkTo(methodOn(PeopleController.class).getByName(people.getName()))
                    .withRel("get-people-by-name"));
            people.add(linkTo(methodOn(PeopleController.class).getByRegistry(people.getTaxpayerRegistry()))
                    .withRel("get-people-by-taxpayer"));
            people.add(linkTo(methodOn(PeopleController.class).getByCategory(people.getCategory().name()))
                    .withRel("get-people-by-category"));
            people.add(linkTo(methodOn(PeopleController.class).getByAddressId(people.getAddress().getId()))
                    .withRel("get-people-by-address-id"));
            people.add(linkTo(methodOn(PeopleController.class).getByUserId(people.getUser().getId()))
                    .withRel("get-people-by-user-id"));
        }

        ListResponseDto response = new ListResponseDto();
        response.setObjects(peoples);
        response.setSelfLink(linkTo(methodOn(PeopleController.class).getAll()).withSelfRel());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/peoples/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        People people = this.peopleService.findById(id);

        people.add(linkTo(methodOn(PeopleController.class).getByName(people.getName()))
                .withRel("get-people-by-name"));
        people.add(linkTo(methodOn(PeopleController.class).getByRegistry(people.getTaxpayerRegistry()))
                .withRel("get-people-by-taxpayer"));
        people.add(linkTo(methodOn(PeopleController.class).getByCategory(people.getCategory().name()))
                .withRel("get-people-by-category"));
        people.add(linkTo(methodOn(PeopleController.class).getByAddressId(people.getAddress().getId()))
                .withRel("get-people-by-address-id"));
        people.add(linkTo(methodOn(PeopleController.class).getByUserId(people.getUser().getId()))
                .withRel("get-people-by-user-id"));
        people.add(linkTo(methodOn(PeopleController.class).getAll())
                .withRel("get-all-peoples"));
        people.add(linkTo(methodOn(PeopleController.class).getById(people.getId()))
                .withSelfRel());

        return ResponseEntity.ok(people);
    }

    @GetMapping("/peoples-name/{peopleName}")
    public ResponseEntity<?> getByName(@PathVariable("peopleName") String peopleName) {
        List<People> peoples = this.peopleService.findByName(peopleName);

        for (People people : peoples) {
            people.add(linkTo(methodOn(PeopleController.class).getById(people.getId()))
                    .withRel("get-people-by-id"));
            people.add(linkTo(methodOn(PeopleController.class).getByRegistry(people.getTaxpayerRegistry()))
                    .withRel("get-people-by-taxpayer"));
            people.add(linkTo(methodOn(PeopleController.class).getByCategory(people.getCategory().name()))
                    .withRel("get-people-by-category"));
            people.add(linkTo(methodOn(PeopleController.class).getByAddressId(people.getAddress().getId()))
                    .withRel("get-people-by-address-id"));
            people.add(linkTo(methodOn(PeopleController.class).getByUserId(people.getUser().getId()))
                    .withRel("get-people-by-user-id"));
        }

        ListResponseDto response = new ListResponseDto();
        response.setObjects(peoples);
        response.setSelfLink(linkTo(methodOn(PeopleController.class).getByName(peopleName)).withSelfRel());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/peoples-registry/{taxpayer}")
    public ResponseEntity<?> getByRegistry(@PathVariable("taxpayer") String taxpayer) {
        People people = this.peopleService.findByRegistry(taxpayer);

        people.add(linkTo(methodOn(PeopleController.class).getById(people.getId()))
                .withRel("get-people-by-id"));
        people.add(linkTo(methodOn(PeopleController.class).getByCategory(people.getCategory().name()))
                .withRel("get-people-by-category"));
        people.add(linkTo(methodOn(PeopleController.class).getByName(people.getName()))
                .withRel("get-people-by-name"));
        people.add(linkTo(methodOn(PeopleController.class).getByAddressId(people.getAddress().getId()))
                .withRel("get-people-by-address-id"));
        people.add(linkTo(methodOn(PeopleController.class).getByUserId(people.getUser().getId()))
                .withRel("get-people-by-user-id"));
        people.add(linkTo(methodOn(PeopleController.class).getAll())
                .withRel("get-all-peoples"));
        people.add(linkTo(methodOn(PeopleController.class).getByRegistry(people.getTaxpayerRegistry()))
                .withSelfRel());

        return ResponseEntity.ok(people);
    }

    @GetMapping("/peoples-category/{category}")
    public ResponseEntity<?> getByCategory(@PathVariable("category") String category) {
        List<People> peoples = this.peopleService.findByCategory(category);

        for (People people : peoples) {
            people.add(linkTo(methodOn(PeopleController.class).getById(people.getId()))
                    .withRel("get-people-by-id"));
            people.add(linkTo(methodOn(PeopleController.class).getByName(people.getName()))
                    .withRel("get-people-by-name"));
            people.add(linkTo(methodOn(PeopleController.class).getByRegistry(people.getTaxpayerRegistry()))
                    .withRel("get-people-by-taxpayer"));
            people.add(linkTo(methodOn(PeopleController.class).getByAddressId(people.getAddress().getId()))
                    .withRel("get-people-by-address-id"));
            people.add(linkTo(methodOn(PeopleController.class).getByUserId(people.getUser().getId()))
                    .withRel("get-people-by-user-id"));
        }

        ListResponseDto response = new ListResponseDto();
        response.setObjects(peoples);
        response.setSelfLink(linkTo(methodOn(PeopleController.class).getByCategory(category)).withSelfRel());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/peoples-address/{addressId}")
    public ResponseEntity<?> getByAddressId(@PathVariable("addressId") Long addressId) {
        People people = this.peopleService.findByAddressId(addressId);

        people.add(linkTo(methodOn(PeopleController.class).getById(people.getId()))
                .withRel("get-people-by-id"));
        people.add(linkTo(methodOn(PeopleController.class).getByRegistry(people.getTaxpayerRegistry()))
                .withRel("get-people-by-taxpayer"));
        people.add(linkTo(methodOn(PeopleController.class).getByName(people.getName()))
                .withRel("get-people-by-name"));
        people.add(linkTo(methodOn(PeopleController.class).getByCategory(people.getCategory().name()))
                .withRel("get-people-by-category"));
        people.add(linkTo(methodOn(PeopleController.class).getByUserId(people.getUser().getId()))
                .withRel("get-people-by-user-id"));
        people.add(linkTo(methodOn(PeopleController.class).getAll())
                .withRel("get-all-peoples"));
        people.add(linkTo(methodOn(PeopleController.class).getByAddressId(addressId))
                .withSelfRel());

        return ResponseEntity.ok(people);
    }

    @GetMapping("/peoples-user/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable("userId") Long userId) {
        People people = this.peopleService.findByUserId(userId);

        people.add(linkTo(methodOn(PeopleController.class).getById(people.getId()))
                .withRel("get-people-by-id"));
        people.add(linkTo(methodOn(PeopleController.class).getByRegistry(people.getTaxpayerRegistry()))
                .withRel("get-people-by-taxpayer"));
        people.add(linkTo(methodOn(PeopleController.class).getByName(people.getName()))
                .withRel("get-people-by-name"));
        people.add(linkTo(methodOn(PeopleController.class).getByCategory(people.getCategory().name()))
                .withRel("get-people-by-category"));
        people.add(linkTo(methodOn(PeopleController.class).getByAddressId(people.getAddress().getId()))
                .withRel("get-people-by-address-id"));
        people.add(linkTo(methodOn(PeopleController.class).getAll())
                .withRel("get-all-peoples"));
        people.add(linkTo(methodOn(PeopleController.class).getByAddressId(userId))
                .withSelfRel());

        return ResponseEntity.ok(people);
    }

    @PostMapping("/peoples")
    public ResponseEntity<?> save(@Valid @RequestBody People people) {
        People peoples = this.peopleService.save(people);

        peoples.add(linkTo(methodOn(PeopleController.class).getById(people.getId()))
                .withRel("get-people-by-id"));
        peoples.add(linkTo(methodOn(PeopleController.class).getByRegistry(people.getTaxpayerRegistry()))
                .withRel("get-people-by-taxpayer"));
        peoples.add(linkTo(methodOn(PeopleController.class).getByName(people.getName()))
                .withRel("get-people-by-name"));
        peoples.add(linkTo(methodOn(PeopleController.class).getByCategory(people.getCategory().name()))
                .withRel("get-people-by-category"));
        peoples.add(linkTo(methodOn(PeopleController.class).getByAddressId(people.getAddress().getId()))
                .withRel("get-people-by-address-id"));
        peoples.add(linkTo(methodOn(PeopleController.class).getByUserId(people.getUser().getId()))
                .withRel("get-people-by-user-id"));
        peoples.add(linkTo(methodOn(PeopleController.class).getAll())
                .withRel("get-all-peoples"));

        return new ResponseEntity<>(peoples, HttpStatus.CREATED);
    }

    @PatchMapping("/peoples/{id}")
    public ResponseEntity<?> updatePeople(
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
            People people = this.peopleService.update(id, keys.get(0), values.get(0));

            people.add(linkTo(methodOn(PeopleController.class).getById(people.getId()))
                    .withRel("get-people-by-id"));
            people.add(linkTo(methodOn(PeopleController.class).getByRegistry(people.getTaxpayerRegistry()))
                    .withRel("get-people-by-taxpayer"));
            people.add(linkTo(methodOn(PeopleController.class).getByName(people.getName()))
                    .withRel("get-people-by-name"));
            people.add(linkTo(methodOn(PeopleController.class).getByCategory(people.getCategory().name()))
                    .withRel("get-people-by-category"));
            people.add(linkTo(methodOn(PeopleController.class).getByAddressId(people.getAddress().getId()))
                    .withRel("get-people-by-address-id"));
            people.add(linkTo(methodOn(PeopleController.class).getByUserId(people.getUser().getId()))
                    .withRel("get-people-by-user-id"));
            people.add(linkTo(methodOn(PeopleController.class).getAll())
                    .withRel("get-all-peoples"));

            return ResponseEntity.ok(people);
        }
    }

    @PutMapping("/peoples/{id}")
    public ResponseEntity<?> updatePeople(@PathVariable("id") Long id, @RequestBody People peoples) {
        People people = this.peopleService.update(id, peoples);

        people.add(linkTo(methodOn(PeopleController.class).getById(people.getId()))
                .withRel("get-people-by-id"));
        people.add(linkTo(methodOn(PeopleController.class).getByRegistry(people.getTaxpayerRegistry()))
                .withRel("get-people-by-taxpayer"));
        people.add(linkTo(methodOn(PeopleController.class).getByName(people.getName()))
                .withRel("get-people-by-name"));
        people.add(linkTo(methodOn(PeopleController.class).getByCategory(people.getCategory().name()))
                .withRel("get-people-by-category"));
        people.add(linkTo(methodOn(PeopleController.class).getByAddressId(people.getAddress().getId()))
                .withRel("get-people-by-address-id"));
        people.add(linkTo(methodOn(PeopleController.class).getByUserId(people.getUser().getId()))
                .withRel("get-people-by-user-id"));
        people.add(linkTo(methodOn(PeopleController.class).getAll())
                .withRel("get-all-peoples"));

        return ResponseEntity.ok(people);
    }
}
