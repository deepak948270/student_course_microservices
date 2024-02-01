package com.thapak.student.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseMapper {
    private Integer id;
    private String name;
    private long fee;
    private Integer studentId;
}

