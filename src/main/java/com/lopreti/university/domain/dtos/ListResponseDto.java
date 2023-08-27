package com.lopreti.university.domain.dtos;

import org.springframework.hateoas.Link;

import java.util.List;

public class ListResponseDto {

    private List<?> objects;
    private Link selfLink;

    public List<?> getObjects() {
        return objects;
    }

    public void setObjects(List<?> objects) {
        this.objects = objects;
    }

    public Link getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(Link selfLink) {
        this.selfLink = selfLink;
    }
}
