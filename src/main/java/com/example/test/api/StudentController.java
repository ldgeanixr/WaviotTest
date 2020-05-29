package com.example.test.api;

import com.example.test.model.Student;
import com.example.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public void addStudent(@RequestBody Student student){
        studentService.insertStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping(path = "{id}")
    public Student getStudentById(@PathVariable("id") Long id){
        return studentService.getStudentById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteStudentById(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "{id}")
    public void updateStudentById(@PathVariable("id") Long id, @RequestBody Student studentToUpdate){
        studentService.updateStudent(id, studentToUpdate);
    }



}
