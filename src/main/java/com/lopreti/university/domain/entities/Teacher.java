package com.lopreti.university.domain.entities;

import com.lopreti.university.domain.valueObjects.PeopleCategory;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "people_id", nullable = false)
    private People people;

    @ManyToMany
    @JoinColumn(name = "class_code", nullable = false)
    private List<Class> classList = new ArrayList<>();

}
