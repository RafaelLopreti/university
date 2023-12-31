package com.lopreti.university.adapters.controllers;

import com.lopreti.university.adapters.model.RootEntryPointModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RootEntryPointController {

    @GetMapping
    public RootEntryPointModel root() {
        RootEntryPointModel model = new RootEntryPointModel();
        model.add(linkTo(methodOn(CourseController.class).getAll()).withRel("all-courses"));
        return model;
    }

}
