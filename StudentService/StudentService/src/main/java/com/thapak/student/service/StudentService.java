package com.thapak.student.service;

import com.thapak.student.entity.Student;
import com.thapak.student.mapper.StudentMapper;
import com.thapak.student.repository.StudentRepository;

import java.util.List;

public interface StudentService {

    // create student object
   StudentMapper createStudent(Student student);

   // find student object  by its id
    StudentMapper findStudent(Integer studentId);

    // find all students
    List<StudentMapper> findStudents();

}
