package com.lopreti.university.domain.entities;

import com.lopreti.university.domain.valueObjects.Period;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private Long id;

    private String name;

    private Period period;

    private List<Subjects> subjectsList = new ArrayList<>();

}
