package com.lopreti.university.adapters.controllers;

import com.lopreti.university.domain.dtos.ListResponseDto;
import com.lopreti.university.domain.entities.Student;
import com.lopreti.university.domain.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/university")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<?> getAll() {
        List<Student> students = this.studentService.findAll();

        for (Student student : students) {
            student.add(linkTo(methodOn(StudentController.class).getById(student.getId()))
                    .withRel("get-student-by-id"));
            student.add(linkTo(methodOn(StudentController.class).getByClass(student.getClassCode()))
                    .withRel("get-student-by-class-code"));
        }

        ListResponseDto response = new ListResponseDto();
        response.setObjects(students);
        response.setSelfLink(linkTo(methodOn(StudentController.class).getAll()).withSelfRel());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Student student = this.studentService.findById(id);

        student.add(linkTo(methodOn(StudentController.class).getByClass(student.getClassCode()))
                .withRel("get-student-by-class-code"));
        student.add(linkTo(methodOn(StudentController.class).getAll()).withRel("get-all-students"));
        student.add(linkTo(methodOn(StudentController.class).getById(id))
                .withSelfRel());

        return ResponseEntity.ok(student);
    }

    @GetMapping("/students-class/{classClode}")
    public ResponseEntity<?> getByClass(@PathVariable("classClode") String classCode) {
        List<Student> students = this.studentService.findByClass(classCode);

        for (Student student : students) {
            student.add(linkTo(methodOn(StudentController.class).getById(student.getId()))
                    .withRel("get-student-by-id"));
        }

        ListResponseDto response = new ListResponseDto();
        response.setObjects(students);
        response.setSelfLink(linkTo(methodOn(StudentController.class).getByClass(classCode)).withSelfRel());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/students")
    public ResponseEntity<?> save(@Valid @RequestBody Student student) {
        Student students = this.studentService.save(student);

        students.add(linkTo(methodOn(StudentController.class).getById(student.getId()))
                .withRel("get-student-by-id"));
        students.add(linkTo(methodOn(StudentController.class).getByClass(student.getClassCode()))
                .withRel("get-student-by-class-code"));
        students.add(linkTo(methodOn(StudentController.class).getAll())
                .withRel("get-all-students"));

        return new ResponseEntity<>(students, HttpStatus.CREATED);
    }

    @PatchMapping("/students/{id}/{classCode}")
    public ResponseEntity<?> updateClass(@PathVariable("id") Long id, @PathVariable("classCode") String classCode) {
        Student student = this.studentService.update(id, classCode);

        student.add(linkTo(methodOn(StudentController.class).getById(id))
                .withRel("get-student-by-id"));
        student.add(linkTo(methodOn(StudentController.class).getByClass(classCode))
                .withRel("get-student-by-class-code"));
        student.add(linkTo(methodOn(StudentController.class).getAll())
                .withRel("get-all-students"));

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Student students) {
        Student student = this.studentService.update(id, students);

        student.add(linkTo(methodOn(StudentController.class).getById(id))
                .withRel("get-student-by-id"));
        student.add(linkTo(methodOn(StudentController.class).getByClass(student.getClassCode()))
                .withRel("get-student-by-class-code"));
        student.add(linkTo(methodOn(StudentController.class).getAll())
                .withRel("get-all-students"));

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

}
