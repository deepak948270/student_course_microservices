package com.thapak.student.entity;


import com.thapak.student.mapper.CourseMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private  Integer id;
    @Column(name = "student_name")
    private  String name;

    @Column(name = "student_email")
    private String email;

    @Transient // try not to save into db
    private List<CourseMapper> courses;

}
