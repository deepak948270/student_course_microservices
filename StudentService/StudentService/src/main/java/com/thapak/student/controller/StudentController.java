package com.thapak.student.controller;

import com.thapak.student.entity.Student;
import com.thapak.student.mapper.CourseMapper;
import com.thapak.student.mapper.StudentMapper;
import com.thapak.student.service.StudentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // create Student@PostMapping
    @PostMapping
    public ResponseEntity<StudentMapper> createStudent(@RequestBody Student student) {
        StudentMapper studentMapper = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentMapper);

    }

    // get student by id
    @CircuitBreaker(name = "courseBreaker", fallbackMethod = "courseByIdFallBack")
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentMapper> getStudent(@PathVariable Integer studentId) {
        //  log.info(exception.getMessage());
        StudentMapper studentMapper = studentService.findStudent(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(studentMapper);
    }

    public ResponseEntity<StudentMapper> courseByIdFallBack(@PathVariable Integer studentId, Exception exception) {
        StudentMapper studentMapper = StudentMapper.builder().id(1).name("dummy-person").email("dummy1@gmail.com").courses(new ArrayList<CourseMapper>()).build();
        return ResponseEntity.status(HttpStatus.OK).body(studentMapper);
    }


    @CircuitBreaker(name = "courseBreaker", fallbackMethod = "courseBreakerFallBack")
    @GetMapping
    public ResponseEntity<List<StudentMapper>> getStudents() {
        List<StudentMapper> studentsMapper = studentService.findStudents();
        return ResponseEntity.status(HttpStatus.OK).body(studentsMapper);
    }

    public ResponseEntity<List<StudentMapper>> courseBreakerFallBack(Exception exception) {
        //  log.info(exception.getMessage());
        StudentMapper studentMapper1 = StudentMapper.builder().id(1).name("dummy-person-1").email("dummy1@gmail.com").courses(new ArrayList<CourseMapper>()).build();
        StudentMapper studentMapper2 = StudentMapper.builder().id(1).name("dummy-person-2").email("dummy2@gmail.com").courses(new ArrayList<CourseMapper>()).build();
        return ResponseEntity.status(HttpStatus.OK).body(List.of(studentMapper1, studentMapper2));

    }


}
