package com.lopreti.university.utils;

import com.google.gson.Gson;
import com.lopreti.university.domain.entities.Address;
import com.lopreti.university.domain.entities.People;
import com.lopreti.university.domain.entities.Student;
import com.lopreti.university.domain.entities.Users;
import com.lopreti.university.domain.valueObjects.Email;
import com.lopreti.university.domain.valueObjects.Password;
import com.lopreti.university.domain.valueObjects.PeopleCategory;
import com.lopreti.university.domain.valueObjects.UserStatus;

public class GenerateJson {

    public static void main(String[] args) {
        System.out.println("The JSON representation of Object is ");

        // PEOPLE JSON
        // System.out.println(new Gson().toJson(getPeople()));

        // STUDENT JSON
        System.out.println(new Gson().toJson(getStudent()));
    }

    public static People getPeople() {
        People people = new People();

        people.setId(2L);
        people.setUsers(new Users(
                2L,
                new Email("test@example.com"),
                new Password("P@ssw0rd"),
                UserStatus.ACTIVE
        ));
        people.setName("John");
        people.setTaxpayerRegistry("123.456.789-00");
        people.setAddress(new Address(
                2L,
                "street",
                "number",
                "city",
                "neighborhood",
                12345678L,
                "country"
        ));
        people.setCategory(PeopleCategory.STUDENT);
        return people;
    }

    public static Student getStudent() {
        Student student = new Student();

        student.setId(2L);
        student.setPeople(getPeople());
        student.setClassCode("UNI-001");

        return student;
    }

}
