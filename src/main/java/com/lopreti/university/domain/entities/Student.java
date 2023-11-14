package com.lopreti.university.domain.entities;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

@Entity
public class Student extends RepresentationModel<Student> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "class_code", nullable = false)
    private String classCode;

    @OneToOne
    @JoinColumn(name = "people_id", nullable = false)
    private People people;

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public Student() {}

    public Student(Long id, String classCode, People people) {
        this.id = id;
        this.classCode = classCode;
        this.people = people;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassCode() {
        return classCode;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

}
