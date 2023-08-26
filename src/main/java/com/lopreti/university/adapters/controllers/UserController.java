package com.lopreti.university.adapters.controllers;

import com.lopreti.university.domain.entities.Users;
import com.lopreti.university.domain.exception.MoreThanOneUpdateException;
import com.lopreti.university.domain.exception.WithoutFieldUpdateException;
import com.lopreti.university.domain.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/university")
public class UserController {

    // TODO IMPLEMENT HYPERMIDIA AND HANDLERS

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<Users> getAll() {
        return this.userService.findAll();
    }

    @GetMapping("/users/{id}")
    public Users getById(@PathVariable Long id) {
        Users user = this.userService.findById(id);
        user.add(linkTo(methodOn(UserController.class).getById(user.getId())).withSelfRel());

        return this.userService.findById(id);
    }

    @GetMapping("/users-email/{email}")
    public Users getByEmail(@PathVariable("email") String email) {
        return this.userService.findByEmail(email);
    }

    @PostMapping("/users")
    public ResponseEntity save(@Valid @RequestBody Users user) {
        this.userService.save(user);
        return new ResponseEntity("User created.", HttpStatus.CREATED);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<?> update(
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
            userService.update(id, keys.get(0), values.get(0));
            return new ResponseEntity<>(format("User %s updated.", keys.get(0)), HttpStatus.OK);
        }
    }

}
