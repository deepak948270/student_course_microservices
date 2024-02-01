package com.thapak.student.mapper;

import com.thapak.student.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentMapper {
    private  Integer id;
    private  String name;
    private String email;
    private List<CourseMapper> courses;
}
