package com.corelia.StudentManagment.config;

import com.corelia.StudentManagment.model.Student;
import com.corelia.StudentManagment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class StartUpProject implements CommandLineRunner {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired

    private StudentRepository studentRepository;
    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS student_db;");
        jdbcTemplate.execute(" CREATE TABLE students ( " +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "email VARCHAR(100) UNIQUE NOT NULL," +
                "age INT NOT NULL)");
        if(studentRepository.count() == 0){
            studentRepository.insert(new Student("Mohammed", "mohammed@gmail.com", 20));
            studentRepository.insert(new Student("Salma", "salma@gmail.com", 28));
            studentRepository.insert(new Student("Ahmed", "ahmed@gmail.com", 34));
        }

    }
}
