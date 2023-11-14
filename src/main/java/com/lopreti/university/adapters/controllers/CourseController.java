package com.lopreti.university.adapters.controllers;

import com.lopreti.university.domain.dtos.ListResponseDto;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/university")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public ResponseEntity<?> getAll() {
        List<Course> courses = this.courseService.findAll();

        for (Course courseObject : courses) {
            courseObject.add(linkTo(methodOn(CourseController.class).getById(courseObject.getId()))
                    .withRel("get-class-by-id"));
            courseObject.add(linkTo(methodOn(CourseController.class).getByName(courseObject.getName()))
                    .withRel("get-class-by-name"));
            courseObject.add(linkTo(methodOn(CourseController.class).getByPeriod(courseObject.getPeriod().name()))
                    .withRel("get-class-by-period"));
        }

        ListResponseDto response = new ListResponseDto();
        response.setObjects(courses);
        response.setSelfLink(linkTo(methodOn(CourseController.class).getAll()).withSelfRel());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Course course = this.courseService.findById(id);

        course.add(linkTo(methodOn(CourseController.class).getByName(course.getName()))
                .withRel("get-course-by-name"));
        course.add(linkTo(methodOn(CourseController.class).getByPeriod(course.getPeriod().name()))
                .withRel("get-course-by-period"));
        course.add(linkTo(methodOn(CourseController.class).getAll())
                .withRel("get-all-courses"));
        course.add(linkTo(methodOn(CourseController.class).getById(course.getId()))
                .withSelfRel());

        return ResponseEntity.ok(course);
    }

    @GetMapping("/courses-name/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name) {
        Course course = this.courseService.findByName(name);

        course.add(linkTo(methodOn(CourseController.class).getById(course.getId()))
                .withRel("get-course-by-id"));
        course.add(linkTo(methodOn(CourseController.class).getByPeriod(course.getPeriod().name()))
                .withRel("get-course-by-period"));
        course.add(linkTo(methodOn(CourseController.class).getAll())
                .withRel("get-all-courses"));
        course.add(linkTo(methodOn(CourseController.class).getByName(course.getName())).withSelfRel());

        return ResponseEntity.ok(course);
    }

    @GetMapping("/courses-period/{period}")
    public ResponseEntity<?> getByPeriod(@PathVariable("period") String period) {
        List<Course> courses = this.courseService.findByPeriod(period);

        for (Course courseObject : courses) {
            courseObject.add(linkTo(methodOn(CourseController.class).getById(courseObject.getId()))
                    .withRel("get-course-by-id"));
            courseObject.add(linkTo(methodOn(CourseController.class).getByName(courseObject.getName()))
                    .withRel("get-course-by-name"));
        }

        ListResponseDto response = new ListResponseDto();
        response.setObjects(courses);
        response.setSelfLink(linkTo(methodOn(CourseController.class).getByPeriod(period)).withSelfRel());

        return ResponseEntity.ok(response);

    }

    @PostMapping("/courses")
    public ResponseEntity<?> save(@Valid @RequestBody Course course) {
        Course courses = this.courseService.save(course);

        courses.add(linkTo(methodOn(CourseController.class).getById(courses.getId()))
                .withRel("get-course-by-id"));
        courses.add(linkTo(methodOn(CourseController.class).getByPeriod(courses.getPeriod().name()))
                .withRel("get-course-by-period"));
        course.add(linkTo(methodOn(CourseController.class).getByName(course.getName()))
                .withRel("get-course-by-name"));
        courses.add(linkTo(methodOn(CourseController.class).getAll())
                .withRel("get-all-courses"));

        return new ResponseEntity<>(courses, HttpStatus.CREATED);
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
            Course courses = this.courseService.update(id, keys.get(0), values.get(0));

            courses.add(linkTo(methodOn(CourseController.class).getById(courses.getId()))
                    .withRel("get-course-by-id"));
            courses.add(linkTo(methodOn(CourseController.class).getByPeriod(courses.getPeriod().name()))
                    .withRel("get-course-by-period"));
            courses.add(linkTo(methodOn(CourseController.class).getByName(courses.getName()))
                    .withRel("get-course-by-name"));
            courses.add(linkTo(methodOn(CourseController.class).getAll())
                    .withRel("get-all-courses"));

            return ResponseEntity.ok(courses);
        }
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Course courses) {
        Course course = this.courseService.update(id, courses);

        courses.add(linkTo(methodOn(CourseController.class).getById(course.getId()))
                .withRel("get-course-by-id"));
        courses.add(linkTo(methodOn(CourseController.class).getByPeriod(course.getPeriod().name()))
                .withRel("get-course-by-period"));
        course.add(linkTo(methodOn(CourseController.class).getByName(course.getName()))
                .withRel("get-course-by-name"));
        courses.add(linkTo(methodOn(CourseController.class).getAll())
                .withRel("get-all-courses"));

        return new ResponseEntity<>(course, HttpStatus.OK);
    }

}
