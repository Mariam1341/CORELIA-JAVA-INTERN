package com.corelia.StudentManagment.repository;

import com.corelia.StudentManagment.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository {
    int count();
    Student findById(Long id);
    List<Student> getAllStudents();
    int insert(Student student);
    int update (Student student);
    int delete(Long id);
}
