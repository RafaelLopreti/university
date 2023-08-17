package com.lopreti.university.domain.entities;

import com.lopreti.university.domain.valueObjects.PeopleCategory;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher extends People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinColumn(name = "class_code", nullable = false)
    private List<Class> classList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private final PeopleCategory category = PeopleCategory.TEACHER;

}
