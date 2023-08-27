package com.lopreti.university.adapters.controllers;

import com.lopreti.university.adapters.model.RootEntryPointModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootEntryPointController {

    @GetMapping
    public RootEntryPointModel root() {
        RootEntryPointModel model = new RootEntryPointModel();

        //model.add(linkTo()); // TODO LIST COURSES

        return model;
    }

}
