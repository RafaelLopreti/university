package com.lopreti.university.domain.dtos;

import java.util.List;

public class ListRequestDto {

    private List<Long> peopleList;

    private List<Long> subjectList;

    private List<Long> teacherList;

    public List<Long> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(List<Long> peopleList) {
        this.peopleList = peopleList;
    }

    public List<Long> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Long> subjectList) {
        this.subjectList = subjectList;
    }

    public List<Long> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Long> teacherList) {
        this.teacherList = teacherList;
    }
}
