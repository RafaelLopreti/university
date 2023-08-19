package com.lopreti.university.adapters.controllers;

import com.lopreti.university.domain.entities.Student;
import com.lopreti.university.domain.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/university")
public class StudentController {

    // TODO IMPLEMENT HYPERMIDIA AND HANDLERS

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAll() {
        return this.studentService.findAll();
    }

    @GetMapping("/students/{id}")
    public Student getById(@PathVariable Long id) {
        return this.studentService.findById(id);
    }

    @GetMapping("/students/{classClode}")
    public List<Student> getByClass(@PathVariable String classCode) {
        return this.studentService.findByClass(classCode);
    }

    @PostMapping("/students")
    public ResponseEntity save(@Valid @RequestBody Student student) {
        this.studentService.save(student);
        return new ResponseEntity("Student created!", HttpStatus.CREATED);
    }

    @PatchMapping("/students/{id}/{classCode}")
    public ResponseEntity<Student> updateClass(@PathVariable Long id, String classCode) {
        this.studentService.update(id, classCode);
        return new ResponseEntity("Student class code update!", HttpStatus.OK);
    }

}
