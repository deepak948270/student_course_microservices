package com.thapak.course.controller;

import com.thapak.course.entity.Course;
import com.thapak.course.mapper.CourseMapper;
import com.thapak.course.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    // create Course
    @PostMapping
    public ResponseEntity<CourseMapper> createCourse(@RequestBody Course course) {
        CourseMapper courseMapper = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseMapper);

    }

    // get Course by id
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseMapper> getCourse(@PathVariable Integer courseId) {
        CourseMapper courseMapper = courseService.findCourse(courseId);
        return ResponseEntity.status(HttpStatus.OK).body(courseMapper);
    }

    // get all courses
    @GetMapping
    public ResponseEntity<List<CourseMapper>> getCourses() {
        List<CourseMapper> courseMappers = courseService.findCourses();
        return ResponseEntity.status(HttpStatus.OK).body(courseMappers);
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<List<CourseMapper>> getCousesByStudentId(@PathVariable Integer studentId) {
        List<CourseMapper> courseMappers = courseService.findByStudentId(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(courseMappers);
    }

}
