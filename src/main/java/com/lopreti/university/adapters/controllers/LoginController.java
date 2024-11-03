package com.lopreti.university.adapters.controllers;

import com.lopreti.university.domain.dtos.LoginDto;
import com.lopreti.university.domain.entities.People;
import com.lopreti.university.domain.entities.Users;
import com.lopreti.university.domain.services.PeopleService;
import com.lopreti.university.domain.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginController {

    private final UserService userService;
    private final PeopleService peopleService;

    public LoginController(UserService userService, PeopleService peopleService) {
        this.userService = userService;
        this.peopleService = peopleService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> requestLogin(@RequestBody LoginDto loginDto) {
        Optional<Users> usersOptional = userService.findToLogin(loginDto.getEmail(), loginDto.getPassword());
        if (usersOptional.isPresent()) {
            People people = peopleService.findByUserId(usersOptional.get().getId());
            return new ResponseEntity<>(people, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
