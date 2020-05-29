package com.example.test.dao;

import com.example.test.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("mysql")
public class MySqlDataAccessService implements StudentDao{


    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public int insertStudent(Student student) {
        jdbcTemplate.update(
                "INSERT INTO Student (studentName) VALUES (?)",
                student.getStudentName()
        );
        return 1;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        students.addAll(
                jdbcTemplate.query("Select * from Student;",
                        new BeanPropertyRowMapper<>(Student.class)));
        return students;
    }

    @Override
    public int deleteStudentById(Long id) {
        jdbcTemplate.update("delete from Student where studentId = ?", id);
        return 1;
    }

    @Override
    public int updateStudentById(Long id, Student student) {
        jdbcTemplate.update("update Student set studentName = ? where studentId = ?",
                student.getStudentName(), id);
        return 1;
    }

    @Override
    public Optional<Student> selectStudentById(Long id) {
        List<Student> students = new ArrayList<>();
        students.addAll(
                jdbcTemplate.query("Select * from Student where studentId = ?;", new Object[]{id},
                        new BeanPropertyRowMapper<>(Student.class)));
        return students.stream().filter(student -> student.getStudentId().equals(id)).findFirst();
    }
}
