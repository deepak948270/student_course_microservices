package com.thapak.student.service.impl;

import com.thapak.student.entity.Student;
import com.thapak.student.external.client.CourseClient;
import com.thapak.student.mapper.CourseMapper;
import com.thapak.student.mapper.StudentMapper;
import com.thapak.student.repository.StudentRepository;
import com.thapak.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    private final CourseClient courseClient;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper, CourseClient courseClient) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
        this.courseClient = courseClient;
    }

    @Override
    public StudentMapper createStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        StudentMapper studentMapper = modelMapper.map(savedStudent, StudentMapper.class);
        ResponseEntity<List<CourseMapper>> cousesByStudentId = courseClient.getCousesByStudentId(studentMapper.getId());
        List<CourseMapper> listOfCourses = cousesByStudentId.getBody();
        studentMapper.setCourses(listOfCourses);
        return studentMapper;
    }

    @Override
    public StudentMapper findStudent(Integer studentId) {
        Student retrivedStudent = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("student not found"));
        StudentMapper studentMapper = modelMapper.map(retrivedStudent, StudentMapper.class);
        ResponseEntity<List<CourseMapper>> cousesByStudentId = courseClient.getCousesByStudentId(studentId);
        List<CourseMapper> listOfCourses = cousesByStudentId.getBody();
        studentMapper.setCourses(listOfCourses);
        // System.out.println(studentMapper);
        return studentMapper;
    }


    @Override
    public List<StudentMapper> findStudents() {
        int studentId;

        List<Student> retrivedStudents = studentRepository.findAll();

       // StudentMapper[] studentMapperArray = modelMapper.map(retrivedStudents, StudentMapper[].class);
       // List<StudentMapper> listOfStudentMapper1 = Arrays.asList(studentMapperArray);

        List<StudentMapper> studentMappers = retrivedStudents.stream().map(student -> modelMapper.map(student, StudentMapper.class)).collect(Collectors.toList());
        List<StudentMapper> listOfStudentMappers = studentMappers.stream().map(studentMapper -> {
            studentMapper.setCourses(courseClient.getCousesByStudentId(studentMapper.getId()).getBody());
            return studentMapper;
        }).collect(Collectors.toList());


        log.info(studentMappers.toString());
        return listOfStudentMappers;
    }
}
