package com.lopreti.university.adapters.controllers;

import com.lopreti.university.domain.dtos.ListRequestDto;
import com.lopreti.university.domain.dtos.ListResponseDto;
import com.lopreti.university.domain.entities.Subjects;
import com.lopreti.university.domain.services.SubjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/university")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/subjects")
    public ResponseEntity<?> getAll() {
        List<Subjects> subjects = this.subjectService.findAll();

        for (Subjects subject : subjects) {
            subject.add(linkTo(methodOn(SubjectController.class).getById(subject.getId())).withRel("get-subject-by-id"));
            subject.add(linkTo(methodOn(SubjectController.class).getByName(subject.getName())).withRel("get-subject-by-name"));
        }

        ListResponseDto response = new ListResponseDto();
        response.setObjects(subjects);
        response.setSelfLink(linkTo(methodOn(SubjectController.class).getAll()).withSelfRel());

        return ResponseEntity.ok(response);

    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Subjects subject = this.subjectService.findById(id);

        subject.add(linkTo(methodOn(SubjectController.class).getByName(subject.getName())).withRel("get-subject-by-name"));
        subject.add(linkTo(methodOn(SubjectController.class).getAll()).withRel("get-all-subject"));
        subject.add(linkTo(methodOn(SubjectController.class).getById(id)).withSelfRel());

        return ResponseEntity.ok(subject);
    }

    @GetMapping("/subjects-name/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name) {
        Subjects subject = this.subjectService.findByName(name);

        subject.add(linkTo(methodOn(SubjectController.class).getById(subject.getId())).withRel("get-subject-by-id"));
        subject.add(linkTo(methodOn(SubjectController.class).getAll()).withRel("get-all-subject"));
        subject.add(linkTo(methodOn(SubjectController.class).getByName(name)).withSelfRel());

        return ResponseEntity.ok(subject);
    }

    @PostMapping("/subjects")
    public ResponseEntity<?> save(@Valid @RequestBody Subjects subject) {
        Subjects subjects = this.subjectService.save(subject);

        subjects.add(linkTo(methodOn(SubjectController.class).getById(subject.getId())).withRel("get-subject-by-id"));
        subjects.add(linkTo(methodOn(SubjectController.class).getByName(subject.getName())).withRel("get-subject-by-name"));
        subjects.add(linkTo(methodOn(SubjectController.class).getAll()).withRel("get-all-subject"));

        return new ResponseEntity<>(subjects, HttpStatus.CREATED);
    }

    @PatchMapping("/subjects/{id}/updateTeacherList")
    public ResponseEntity<?> updatePeopleList(@PathVariable("id") Long id, @RequestBody ListRequestDto peopleList) {
        Subjects subject = this.subjectService.updateTeacherList(id, peopleList);

        subject.add(linkTo(methodOn(SubjectController.class).getById(id)).withRel("get-subject-by-id"));
        subject.add(linkTo(methodOn(SubjectController.class).getByName(subject.getName())).withRel("get-subject-by-name"));
        subject.add(linkTo(methodOn(SubjectController.class).getAll()).withRel("get-all-subject"));

        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @PatchMapping("/subjects/{id}/{name}")
    public ResponseEntity<?> updateName(@PathVariable("id") Long id, @PathVariable("name") String name) {
        Subjects subject = this.subjectService.updateName(id, name);

        subject.add(linkTo(methodOn(SubjectController.class).getById(id)).withRel("get-subject-by-id"));
        subject.add(linkTo(methodOn(SubjectController.class).getByName(subject.getName())).withRel("get-subject-by-name"));
        subject.add(linkTo(methodOn(SubjectController.class).getAll()).withRel("get-all-subject"));

        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @PutMapping("/subjects/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Subjects subjects) {
        Subjects subject = this.subjectService.update(id, subjects);

        subject.add(linkTo(methodOn(SubjectController.class).getById(id)).withRel("get-subject-by-id"));
        subject.add(linkTo(methodOn(SubjectController.class).getByName(subject.getName())).withRel("get-subject-by-name"));
        subject.add(linkTo(methodOn(SubjectController.class).getAll()).withRel("get-all-subject"));

        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

}
