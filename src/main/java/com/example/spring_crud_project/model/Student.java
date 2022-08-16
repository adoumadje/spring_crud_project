package com.example.spring_crud_project.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class Student {
    private final UUID id;
    @NotBlank
    private final String firstname;
    @NotBlank
    private final String lastname;
    @NotBlank @NotNull
    private final int grade;

    public Student(UUID id,
                   String firstname,
                   String lastname,
                   int grade) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.grade = grade;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getGrade() {
        return grade;
    }
}
