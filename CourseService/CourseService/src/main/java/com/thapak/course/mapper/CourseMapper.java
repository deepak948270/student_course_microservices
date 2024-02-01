package com.thapak.course.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseMapper {
    private Integer id;
    private String name;
    private long fee;
    private Integer studentId;
}
