package com.lopreti.university.domain.entities;

import com.lopreti.university.domain.valueObjects.PeopleCategory;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends People {

    private Long id;

    private List<Class> classList = new ArrayList<>();

    private final PeopleCategory category = PeopleCategory.TEACHER;

}
