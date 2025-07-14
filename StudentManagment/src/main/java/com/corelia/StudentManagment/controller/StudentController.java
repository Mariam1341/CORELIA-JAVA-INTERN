package com.corelia.StudentManagment.controller;

import com.corelia.StudentManagment.model.Student;
import com.corelia.StudentManagment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students/")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("count")
    public ResponseEntity<Integer> countStudents(){
        return ResponseEntity.ok(studentService.count());
    }
    @GetMapping("")
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        return ResponseEntity.ok(studentService.findStudentById(id));
    }
    @PostMapping("add")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
        studentService.addStudent(student);
        return ResponseEntity.ok("Student added Successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Student student){
        student.setId(id);
        studentService.updateStudent(student);
        return ResponseEntity.ok("Student updated Successfully");
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted Successfully");
    }

}
