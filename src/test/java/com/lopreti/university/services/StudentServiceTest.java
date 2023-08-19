package com.lopreti.university.services;

import com.lopreti.university.adapters.repositories.impl.StudentRepositoryImpl;
import com.lopreti.university.domain.entities.Address;
import com.lopreti.university.domain.entities.People;
import com.lopreti.university.domain.entities.Student;
import com.lopreti.university.domain.entities.Users;
import com.lopreti.university.domain.services.StudentService;
import com.lopreti.university.domain.valueObjects.Email;
import com.lopreti.university.domain.valueObjects.Password;
import com.lopreti.university.domain.valueObjects.UserStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepositoryImpl studentRepositoryImpl;

    @Test
    @DisplayName("Find all students")
    public void findAllStudents() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(
                new Student(1L,"UNI-001",
                    new People(1L,
                        new Users(1L, new Email("test@example.com"), new Password("Abc@1234"), UserStatus.ACTIVE),
                            "Find All Student", "111.222.333-44", new Address(1L,
                            "Street", "Number", "City", "Neighborhood",
                            12345678L, "Country"
                        )
                    )
                )
        );

        when(studentRepositoryImpl.findAll()).thenReturn(studentList);
        List<Student> students = studentService.findAll();

        assertNotNull(students);
        assertFalse(students.isEmpty());
        assertEquals(1, studentList.size());
    }

}
