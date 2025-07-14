package com.corelia.StudentManagment.service;


import com.corelia.StudentManagment.model.Student;
import com.corelia.StudentManagment.repository.StudentRepository;
import com.corelia.StudentManagment.repository.implmentation.StudentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;


    public int count(){
        return studentRepository.count();
    }
    public List<Student> getAllStudents(){
        return studentRepository.getAllStudents();
    }
    public int addStudent(Student student){
        return studentRepository.insert(student);
    }
    public int updateStudent(Student student){
        return studentRepository.update(student);
    }
    public int deleteStudent(Long id){
        return studentRepository.delete(id);
    }
    public Student findStudentById(Long id){
        return studentRepository.findById(id);
    }



}
