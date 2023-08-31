package com.lopreti.university.adapters.controllers;

import com.lopreti.university.domain.entities.Course;
import com.lopreti.university.domain.exception.MoreThanOneUpdateException;
import com.lopreti.university.domain.exception.WithoutFieldUpdateException;
import com.lopreti.university.domain.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;

@RestController
@RequestMapping("/api/v1/university")
public class CourseController {

    // TODO IMPLEMENT HYPERMIDIA AND HANDLERS
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public List<Course> getAll() {
        return this.courseService.findAll();
    }

    @GetMapping("/courses/{id}")
    public Course getById(@PathVariable Long id) {
        return this.courseService.findById(id);
    }

    @GetMapping("/courses-name/{name}")
    public Course getByName(@PathVariable("name") String name) {
        return this.courseService.findByName(name);
    }

    @GetMapping("/courses-period/{period}")
    public List<Course> getByPeriod(@PathVariable("period") String period) {
        return this.courseService.findByPeriod(period);
    }

    @PostMapping("/courses")
    public ResponseEntity<?> save(@Valid @RequestBody Course course) {
        this.courseService.save(course);
        return new ResponseEntity<>("Course created.", HttpStatus.CREATED);
    }

    @PatchMapping("/courses/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
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
            courseService.update(id, keys.get(0), values.get(0));
            return new ResponseEntity<>(format("Course %s updated.", keys.get(0)), HttpStatus.OK);
        }
    }

}
