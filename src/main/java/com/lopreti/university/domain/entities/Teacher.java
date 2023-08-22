package com.lopreti.university.domain.entities;

import jakarta.persistence.*;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "people_id", nullable = false)
    private People people;

    @JoinColumn(name = "class_code", nullable = false)
    private String classCode;

    public Teacher() {}

    public Teacher(Long id, String classCode, People people) {
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

    public void setClassList(String classCode) {
        this.classCode = classCode;
    }
}
