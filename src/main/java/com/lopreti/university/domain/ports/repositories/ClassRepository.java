package com.lopreti.university.domain.ports.repositories;

import com.lopreti.university.domain.entities.Class;

public interface ClassRepository {

    public Class findByCode(String classCode);

}
