package com.thapak.course.service;

import com.thapak.course.entity.Course;
import com.thapak.course.mapper.CourseMapper;
import com.thapak.course.repository.CourseRepository;

import java.util.List;

public interface CourseService {

    // create student object
    CourseMapper createCourse(Course course);

    // find student object  by its id
    CourseMapper findCourse(Integer courseId);

    // find all students
    List<CourseMapper> findCourses();

    //find courses by using student id
    List<CourseMapper> findByStudentId(Integer studentId);


}
