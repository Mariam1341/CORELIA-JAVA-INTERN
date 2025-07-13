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

        if(studentRepository.count() == 0){
            studentRepository.insert(new Student("Mohammed", "mohammed@gmail.com", 20));
            studentRepository.insert(new Student("Salma", "salma@gmail.com", 28));
            studentRepository.insert(new Student("Ahmed", "ahmed@gmail.com", 34));
        }

    }
}
