package com.thapak.course.service.impl;

import com.thapak.course.entity.Course;
import com.thapak.course.mapper.CourseMapper;
import com.thapak.course.repository.CourseRepository;
import com.thapak.course.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;
    private ModelMapper modelMapper;

    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CourseMapper createCourse(Course course) {
        Course retrivedCourse = courseRepository.save(course);
        CourseMapper couseMapper = modelMapper.map(retrivedCourse, CourseMapper.class);
        return couseMapper;
    }

    @Override
    public CourseMapper findCourse(Integer courseId) {
        Course retrivedCourse = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("course not found !"));
        CourseMapper courseMapper = modelMapper.map(retrivedCourse, CourseMapper.class);
        return courseMapper;
    }

    @Override
    public List<CourseMapper> findCourses() {
        List<Course> retrivedCourses = courseRepository.findAll();
        List<CourseMapper> courseMappers = retrivedCourses.stream().map((courseEntity) -> modelMapper.map(courseEntity, CourseMapper.class)).collect(Collectors.toList());
        return courseMappers;
    }

    @Override
    public List<CourseMapper> findByStudentId(Integer studentId) {
        List<Course> retrivedCourses = courseRepository.findCourseByStudentId(studentId);
        List<CourseMapper> courseMappers = retrivedCourses.stream().map((courseEntity) -> modelMapper.map(courseEntity, CourseMapper.class)).collect(Collectors.toList());
        log.info(modelMapper.toString());
        return courseMappers;
    }
}
