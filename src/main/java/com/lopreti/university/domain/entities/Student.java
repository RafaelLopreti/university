package com.lopreti.university.domain.entities;

import com.lopreti.university.domain.valueObjects.PeopleCategory;
import jakarta.persistence.*;

@Entity
public class Student extends People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "class_code", nullable = false)
    private String classCode;

    @OneToOne
    @JoinColumn(name = "people_id", nullable = false)
    private People people;

    @Enumerated(EnumType.STRING)
    private final PeopleCategory category = PeopleCategory.STUDENT;

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public Student(Long id, String classCode, People people) {
        this.id = id;
        this.classCode = classCode;
        this.people = people;
    }

}
