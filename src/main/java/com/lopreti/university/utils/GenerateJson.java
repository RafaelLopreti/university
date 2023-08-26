package com.lopreti.university.utils;

import com.google.gson.Gson;
import com.lopreti.university.domain.entities.Address;
import com.lopreti.university.domain.entities.People;
import com.lopreti.university.domain.entities.Student;
import com.lopreti.university.domain.entities.Users;
import com.lopreti.university.domain.valueObjects.PeopleCategory;
import com.lopreti.university.domain.valueObjects.UserStatus;

public class GenerateJson {

    public static void main(String[] args) {
        // PEOPLE BODY JSON
        // System.out.println(new Gson().toJson(getPeople()));

        // STUDENT BODY JSON
        //System.out.println(new Gson().toJson(getStudent()));

        // ADDRESS BODY JSON
        System.out.println(new Gson().toJson(getAddress()));

        // USER BODY JSON
        //System.out.println(new Gson().toJson(getUser()));
    }

    public static People getPeople() {
        People people = new People();

        people.setId(2L);
        people.setUsers(new Users(
                2L,
                "test@example.com",
                "Abc@1234",
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
                "12345678",
                "country",
                people.getUsers()
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

    public static Address getAddress() {
        Address address = new Address();

        address.setId(3L);
        address.setStreet("Street");
        address.setNumber("000");
        address.setCity("City");
        address.setNeighborhood("Neighborhood");
        address.setZipCode("00000000");
        address.setCountry("Country");
        address.setUser(getPeople().getUsers());

        return address;
    }

    public static Users getUser() {
        Users user = new Users();

        user.setId(4L);
        user.setEmail("test4@gmail.com");
        user.setPassword("Abc@1234");
        user.setStatus(UserStatus.ACTIVE);

        return user;
    }

}
