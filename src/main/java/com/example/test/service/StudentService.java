package com.example.test.service;

import com.example.test.dao.StudentDao;
import com.example.test.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentDao studentDao;

    @Autowired
    public StudentService(@Qualifier("mysql")StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public int insertStudent(Student student){
        return studentDao.insertStudent(student);
    }

    public List<Student> getAllStudents(){
        return studentDao.getAllStudents();
    }

    public Optional<Student> getStudentById(Long id){
        return studentDao.selectStudentById(id);
    }

    public int deleteStudent(Long id){
        return studentDao.deleteStudentById(id);
    }

    public int updateStudent(Long id, Student newStudent){
        return studentDao.updateStudentById(id, newStudent);
    }



}
