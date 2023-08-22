package com.lopreti.university.adapters.controllers;

import com.lopreti.university.domain.entities.Teacher;
import com.lopreti.university.domain.services.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/university")
public class TeacherController {

    // TODO IMPLEMENT HYPERMIDIA AND HANDLERS

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public List<Teacher> getAll() {
        return this.teacherService.findAll();
    }

    @GetMapping("/teachers/{id}")
    public Teacher getById(@PathVariable Long id) {
        return this.teacherService.findById(id);
    }

    @GetMapping("/teachers-class/{classClode}")
    public List<Teacher> getByClass(@PathVariable("classClode") String classCode) {
        return this.teacherService.findByClass(classCode);
    }

    @PostMapping("/teachers")
    public ResponseEntity save(@Valid @RequestBody Teacher teacher) {
        this.teacherService.save(teacher);
        return new ResponseEntity("Teacher created.", HttpStatus.CREATED);
    }

    @PatchMapping("/teachers/{id}/{classCode}")
    public ResponseEntity updateClass(@PathVariable("id") Long id, @PathVariable("classCode") String classCode) {
        this.teacherService.update(id, classCode);
        return new ResponseEntity("Teacher class code updated.", HttpStatus.OK);
    }

}
