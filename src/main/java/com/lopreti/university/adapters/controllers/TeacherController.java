package com.lopreti.university.adapters.controllers;

import com.lopreti.university.domain.dtos.ListResponseDto;
import com.lopreti.university.domain.entities.Teacher;
import com.lopreti.university.domain.services.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/university")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public ResponseEntity<?> getAll() {
        List<Teacher> teachers = this.teacherService.findAll();

        for (Teacher teacher : teachers) {
            teacher.add(linkTo(methodOn(TeacherController.class).getById(teacher.getId()))
                    .withRel("get-teacher-by-id"));
            teacher.add(linkTo(methodOn(TeacherController.class).getByClass(teacher.getClassCode()))
                    .withRel("get-teacher-by-class"));
        }

        ListResponseDto response = new ListResponseDto();
        response.setObjects(teachers);
        response.setSelfLink(linkTo(methodOn(TeacherController.class).getAll()).withSelfRel());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Teacher teacher = this.teacherService.findById(id);

        teacher.add(linkTo(methodOn(TeacherController.class).getAll())
                .withRel("get-all-teachers"));
        teacher.add(linkTo(methodOn(TeacherController.class).getByClass(teacher.getClassCode()))
                .withRel("get-teacher-by-class"));
        teacher.add(linkTo(methodOn(TeacherController.class).getById(id))
                .withSelfRel());

        return ResponseEntity.ok(teacher);
    }

    @GetMapping("/teachers-class/{classClode}")
    public ResponseEntity<?> getByClass(@PathVariable("classClode") String classCode) {
        List<Teacher> teachers = this.teacherService.findByClass(classCode);

        for (Teacher teacher : teachers) {
            teacher.add(linkTo(methodOn(TeacherController.class).getById(teacher.getId()))
                    .withRel("get-teacher-by-id"));
        }

        ListResponseDto response = new ListResponseDto();
        response.setObjects(teachers);
        response.setSelfLink(linkTo(methodOn(TeacherController.class).getByClass(classCode)).withSelfRel());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/teachers")
    public ResponseEntity<?> save(@Valid @RequestBody Teacher teacher) {
        Teacher teachers = this.teacherService.save(teacher);

        teachers.add(linkTo(methodOn(TeacherController.class).getById(teacher.getId()))
                .withRel("get-teacher-by-id"));
        teachers.add(linkTo(methodOn(TeacherController.class).getByClass(teacher.getClassCode()))
                .withRel("get-teacher-by-class"));
        teachers.add(linkTo(methodOn(TeacherController.class).getAll())
                .withRel("get-all-teachers"));

        return new ResponseEntity<>(teachers, HttpStatus.CREATED);
    }

    @PatchMapping("/teachers/{id}/{classCode}")
    public ResponseEntity<?> updateClass(@PathVariable("id") Long id, @PathVariable("classCode") String classCode) {
        Teacher teacher = this.teacherService.update(id, classCode);

        teacher.add(linkTo(methodOn(TeacherController.class).getById(id))
                .withRel("get-teacher-by-id"));
        teacher.add(linkTo(methodOn(TeacherController.class).getByClass(classCode))
                .withRel("get-teacher-by-class"));
        teacher.add(linkTo(methodOn(TeacherController.class).getAll())
                .withRel("get-all-teachers"));

        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @PutMapping("/teachers/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Teacher teachers) {
        Teacher teacher = this.teacherService.update(id, teachers);

        teacher.add(linkTo(methodOn(TeacherController.class).getById(id))
                .withRel("get-teacher-by-id"));
        teacher.add(linkTo(methodOn(TeacherController.class).getByClass(teacher.getClassCode()))
                .withRel("get-teacher-by-class"));
        teacher.add(linkTo(methodOn(TeacherController.class).getAll())
                .withRel("get-all-teachers"));

        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

}
