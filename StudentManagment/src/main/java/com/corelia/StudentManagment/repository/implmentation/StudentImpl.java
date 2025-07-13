package com.corelia.StudentManagment.repository.implmentation;

import com.corelia.StudentManagment.mapper.StudentMapper;
import com.corelia.StudentManagment.model.Student;
import com.corelia.StudentManagment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentImpl implements StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int count(){
        return jdbcTemplate.queryForObject("SELECT count(*) FROM students", Integer.class);
    }
    public Student findById(Long id){
        return jdbcTemplate.queryForObject("SELECT * FROM students WHERE id = ?", new Object[] {id},
               new StudentMapper());
    }
    public List<Student> getAllStudents(){
        return jdbcTemplate.query("SELECT * FROM students", new StudentMapper());
    }
    public int insert(Student student){
        return jdbcTemplate.update("INSERT INTO students (name, email, age) VALUES (?, ? , ?)",
                new Object[] {student.getName(), student.getEmail(), student.getAge()});
    }
    public int update (Student student){
        return jdbcTemplate.update("UPDATE students SET name = ?, email = ?, age = ? WHERE id = ?",
                new Object[] {student.getName(), student.getEmail(), student.getAge(), student.getId()});
    }
    public int delete(Long id){
        return jdbcTemplate.update("DELETE FROM students WHERE id = ?",
                new Object[] {id});
    }
}
