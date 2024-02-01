package com.thapak.course.repository;

import com.thapak.course.entity.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//@Repository
public interface CourseRepository extends MongoRepository<Course,Integer> {
    List<Course> findCourseByStudentId(Integer id);
}
