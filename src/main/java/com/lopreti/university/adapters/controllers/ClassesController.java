package com.lopreti.university.adapters.controllers;

import com.lopreti.university.domain.dtos.ListRequestDto;
import com.lopreti.university.domain.dtos.ListResponseDto;
import com.lopreti.university.domain.entities.Classes;
import com.lopreti.university.domain.exception.MoreThanOneUpdateException;
import com.lopreti.university.domain.exception.WithoutFieldUpdateException;
import com.lopreti.university.domain.services.ClassesService;
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
public class ClassesController {

    private final ClassesService classesService;

    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    @GetMapping("/classes")
    public ResponseEntity<?> getAllClasses() {
        List<Classes> classes = this.classesService.findAll();

        for (Classes classObject : classes) {
            classObject.add(linkTo(methodOn(ClassesController.class).getById(classObject.getId())).withRel("get-class-by-id"));
            classObject.add(linkTo(methodOn(ClassesController.class).getByClass(classObject.getCode())).withRel("get-class-by-code"));
        }

        ListResponseDto response = new ListResponseDto();
        response.setObjects(classes);
        response.setSelfLink(linkTo(methodOn(ClassesController.class).getAllClasses()).withSelfRel());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/classes/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Classes classes = this.classesService.findById(id);

        classes.add(linkTo(methodOn(ClassesController.class).getById(classes.getId())).withSelfRel());
        classes.add(linkTo(methodOn(ClassesController.class).getByClass(classes.getCode())).withRel("get-class-by-code"));
        classes.add(linkTo(methodOn(ClassesController.class).getAllClasses()).withRel("get-all-classes"));

        return ResponseEntity.ok(classes);
    }

    @GetMapping("/classes-code/{code}")
    public ResponseEntity<?> getByClass(@PathVariable("code") String code) {
        Classes classes = this.classesService.findByCode(code);

        classes.add(linkTo(methodOn(ClassesController.class).getByClass(classes.getCode())).withSelfRel());
        classes.add(linkTo(methodOn(ClassesController.class).getById(classes.getId())).withRel("get-class-by-id"));
        classes.add(linkTo(methodOn(ClassesController.class).getAllClasses()).withRel("get-all-classes"));

        return ResponseEntity.ok(classes);
    }

    @PostMapping("/classes")
    public ResponseEntity<?> save(@Valid @RequestBody Classes classesSave) {
        Classes classes = this.classesService.save(classesSave);
        classes.add(linkTo(methodOn(ClassesController.class).getById(classesSave.getId())).withSelfRel());
        classes.add(linkTo(methodOn(ClassesController.class).getByClass(classes.getCode())).withRel("get-class-by-code"));
        classes.add(linkTo(methodOn(ClassesController.class).getAllClasses()).withRel("get-all-classes"));

        return new ResponseEntity<>(classes, HttpStatus.CREATED);
    }

    @PatchMapping("/classes-code/{code}")
    public ResponseEntity<?> updateClassCode(
            @PathVariable("code") String code,
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
            Classes classes = this.classesService.updateCode(code, keys.get(0), values.get(0));

            classes.add(linkTo(methodOn(ClassesController.class).getById(classes.getId())).withRel("get-class-by-id"));
            classes.add(linkTo(methodOn(ClassesController.class).getByClass(classes.getCode())).withRel("get-class-by-code"));
            classes.add(linkTo(methodOn(ClassesController.class).getAllClasses()).withRel("get-all-classes"));
            classes.add(linkTo(methodOn(ClassesController.class).updateClassCode(code, allParams)).withSelfRel());

            return ResponseEntity.ok(classes);
        }
    }

    @PatchMapping("/classes/{classCode}/updatePeopleList")
    public ResponseEntity<?> updatePeopleList(@PathVariable("classCode") String classCode, @RequestBody ListRequestDto peopleList) {
        Classes classes = this.classesService.updatePeopleList(classCode, peopleList);

        classes.add(linkTo(methodOn(ClassesController.class).getByClass(classes.getCode())).withRel("get-class-by-code"));
        classes.add(linkTo(methodOn(ClassesController.class).getAllClasses()).withRel("get-all-classes"));
        classes.add(linkTo(methodOn(ClassesController.class).updatePeopleList(classCode, peopleList)).withSelfRel());

        return ResponseEntity.ok(classes);
    }

    @PatchMapping("/classes/{classCode}/updateSubjectsList")
    public ResponseEntity<?> updateSubjectsList(@PathVariable("classCode") String classCode, @RequestBody ListRequestDto subjectList) {
        Classes classes = this.classesService.updateSubjectsList(classCode, subjectList);

        classes.add(linkTo(methodOn(ClassesController.class).getByClass(classes.getCode())).withRel("get-class-by-code"));
        classes.add(linkTo(methodOn(ClassesController.class).getAllClasses()).withRel("get-all-classes"));
        classes.add(linkTo(methodOn(ClassesController.class).updatePeopleList(classCode, subjectList)).withSelfRel());

        return ResponseEntity.ok(classes);
    }

    @PutMapping("/classes/{id}")
    public ResponseEntity<?> updateClasses(@PathVariable("id") Long id, @RequestBody Classes classe) {
        Classes classes = this.classesService.update(id, classe);

        classes.add(linkTo(methodOn(ClassesController.class).getById(classes.getId())).withRel("get-class-by-id"));
        classes.add(linkTo(methodOn(ClassesController.class).getByClass(classes.getCode())).withRel("get-class-by-code"));
        classes.add(linkTo(methodOn(ClassesController.class).getAllClasses()).withRel("get-all-classes"));

        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

}
