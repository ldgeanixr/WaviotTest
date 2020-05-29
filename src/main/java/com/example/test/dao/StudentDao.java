package com.example.test.dao;


import com.example.test.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface StudentDao {


    int insertStudent(Student student);

    List<Student> getAllStudents();

    int deleteStudentById(Long id);

    int updateStudentById(Long id, Student student);

    Optional<Student> selectStudentById(Long id);

}
