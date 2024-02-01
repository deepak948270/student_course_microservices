package com.thapak.course.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Course {
    @Id
    @Field(name = "course_id")
    private Integer id;
    @Field(name = "course_name")
    private String name;
    @Field(name = "course_fee")
    private long fee;
    @Field(name = "student_id")
    private Integer studentId;
}
