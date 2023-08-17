package com.lopreti.university.domain.entities;

import com.lopreti.university.domain.valueObjects.PeopleCategory;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student extends People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "class_code", nullable = false)
    private Class classCode;

    @Enumerated(EnumType.STRING)
    private final PeopleCategory category = PeopleCategory.STUDENT;

}
