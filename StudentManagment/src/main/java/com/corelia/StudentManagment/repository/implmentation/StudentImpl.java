package com.corelia.StudentManagment.repository.implmentation;

import com.corelia.StudentManagment.model.Student;
import com.corelia.StudentManagment.repository.StudentRepository;

import java.util.List;

public class StudentImpl implements StudentRepository {
    public int count(){
        return 0;
    }
    public Student findById(Long id){return null;}
    public List<Student> getAllStudents(){return null;}
    public int insert(Student student){return 0;}
    public int update (Student student){return 0;}
    public int delete(Long id){return 0;}
}
