package com.lopreti.university.domain.entities;

import java.util.ArrayList;
import java.util.List;

public class Class {

    private Long id;

    private String code = "UNI-000";

    private List<People> peopleList = new ArrayList<>();

    private List<Subjects> subjectsList = new ArrayList<>();

}
