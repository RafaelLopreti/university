package com.lopreti.university.adapters.controllers;

import com.lopreti.university.domain.entities.Classes;
import com.lopreti.university.domain.services.ClassesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping("/api/v1/university")
public class ClassesController {

    private final ClassesService classesService;

    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    @GetMapping("/classes")
    public List<Classes> getAll() {
        return this.classesService.findAll();
    }

    @GetMapping("/classes/{id}")
    public Classes getById(@PathVariable Long id) {
        return this.classesService.findById(id);
    }

    @GetMapping("/classes-code/{code}")
    public Classes getByClass(@PathVariable("code") String code) {
        return this.classesService.findByCode(code);
    }

    @PostMapping("/classes")
    public ResponseEntity<?> save(@Valid @RequestBody Classes classes) {
        this.classesService.save(classes);
        return new ResponseEntity<>("Class created.", HttpStatus.CREATED);
    }

    @PatchMapping("/classes/{classCode}/updatePeopleList")
    public ResponseEntity<?> updatePeopleList(@PathVariable("classCode") String classCode, @RequestBody List<Long> newPeopleIds) {
        this.classesService.updatePeopleList(classCode, newPeopleIds);
        return new ResponseEntity<>(format("People list updated for class with code %s.", classCode), HttpStatus.OK);
    }

    @PatchMapping("/classes/{classCode}/updateSubjectsList")
    public ResponseEntity<?> updateSubjectsList(@PathVariable("classCode") String classCode, @RequestBody List<Long> newSubjectsIds) {
        this.classesService.updateSubjectsList(classCode, newSubjectsIds);
        return new ResponseEntity<>(format("Subjects list updated for class with code %s.", classCode), HttpStatus.OK);
    }

}
