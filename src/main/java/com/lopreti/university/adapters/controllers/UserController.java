package com.lopreti.university.adapters.controllers;

import com.lopreti.university.domain.dtos.ListResponseDto;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/university")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAll() {
        List<Users> users = this.userService.findAll();

        for (Users user : users) {
            user.add(linkTo(methodOn(UserController.class).getById(user.getId())).withRel("get-user-by-id"));
            user.add(linkTo(methodOn(UserController.class).getByEmail(user.getEmail())).withRel("get-user-by-email"));
        }

        ListResponseDto response = new ListResponseDto();
        response.setObjects(users);
        response.setSelfLink(linkTo(methodOn(UserController.class).getAll()).withSelfRel());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Users user = this.userService.findById(id);

        user.add(linkTo(methodOn(UserController.class).getAll()).withRel("get-all-users"));
        user.add(linkTo(methodOn(UserController.class).getByEmail(user.getEmail())).withRel("get-user-by-email"));
        user.add(linkTo(methodOn(UserController.class).getById(id)).withSelfRel());

        return ResponseEntity.ok(user);
    }

    @GetMapping("/users-email/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable("email") String email) {
        Users user = this.userService.findByEmail(email);

        user.add(linkTo(methodOn(UserController.class).getAll()).withRel("get-all-users"));
        user.add(linkTo(methodOn(UserController.class).getById(user.getId())).withRel("get-user-by-id"));
        user.add(linkTo(methodOn(UserController.class).getByEmail(user.getEmail())).withSelfRel());

        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public ResponseEntity<?> save(@Valid @RequestBody Users user) {
        Users users = this.userService.save(user);

        users.add(linkTo(methodOn(UserController.class).getAll()).withRel("get-all-users"));
        users.add(linkTo(methodOn(UserController.class).getById(user.getId())).withRel("get-user-by-id"));
        users.add(linkTo(methodOn(UserController.class).getByEmail(user.getEmail())).withRel("get-user-by-email"));

        return new ResponseEntity<>(users, HttpStatus.CREATED);
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
            Users user = this.userService.update(id, keys.get(0), values.get(0));

            user.add(linkTo(methodOn(UserController.class).getAll()).withRel("get-all-users"));
            user.add(linkTo(methodOn(UserController.class).getById(user.getId())).withRel("get-user-by-id"));
            user.add(linkTo(methodOn(UserController.class).getByEmail(user.getEmail())).withRel("get-user-by-email"));

            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Users users) {
        Users user = this.userService.update(id, users);

        user.add(linkTo(methodOn(UserController.class).getAll()).withRel("get-all-users"));
        user.add(linkTo(methodOn(UserController.class).getById(user.getId())).withRel("get-user-by-id"));
        user.add(linkTo(methodOn(UserController.class).getByEmail(user.getEmail())).withRel("get-user-by-email"));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
