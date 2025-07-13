package com.corelia.StudentManagment.dto;

public class StudentDTO {
    private String name;
    private String email;
    private int age;

    public StudentDTO() {
    }

    public StudentDTO(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
