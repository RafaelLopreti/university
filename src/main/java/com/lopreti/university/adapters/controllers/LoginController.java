package com.lopreti.university.adapters.controllers;

import com.lopreti.university.domain.dtos.LoginRequest;
import com.lopreti.university.domain.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/university")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> requestLogin(@RequestBody LoginRequest loginRequest) {
        return userService
                .findToLogin(loginRequest.getEmail(), loginRequest.getPassword())
                .isPresent() ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
