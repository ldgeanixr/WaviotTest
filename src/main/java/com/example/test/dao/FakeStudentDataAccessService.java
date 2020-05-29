package com.example.test.dao;

import com.example.test.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeStudentDataAccessService implements StudentDao {

    private Long id = 1l;

    private static List<Student> DB = new ArrayList<>();


    @Override
    public int insertStudent(Student student) {
        DB.add(new Student(id, student.getStudentName()));
        id++;
        return 1;
    }

    @Override
    public List<Student> getAllStudents() {
        return DB;
    }

    @Override
    public int deleteStudentById(Long id) {
        Optional<Student> student = selectStudentById(id);
        if (student.isEmpty()){
            return 0;
        }
        DB.remove(student.get());
        return 1;
    }

    @Override
    public int updateStudentById(Long id, Student student) {
        return selectStudentById(id).map(s -> {
            int indexToUpdate = DB.indexOf(s);
            if (indexToUpdate >= 0){
                DB.set(indexToUpdate, new Student(id, student.getStudentName()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }

    @Override
    public Optional<Student> selectStudentById(Long id) {
        return DB.stream().filter(student -> student.getStudentId().equals(id)).findFirst();
    }
}
