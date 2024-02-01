package com.thapak.student.external.client;

import com.thapak.student.mapper.CourseMapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(url = "http://localhost:8082/courses",value = "course-client")
//@FeignClient(url = "http://COURSE-SERVICE/courses",value = "course-client") ???
@FeignClient(name = "COURSE-SERVICE") //calling by using application.name ,i.e., and perform load balancing
public interface CourseClient {

    @GetMapping("courses/students/{studentId}")
    public ResponseEntity<List<CourseMapper>> getCousesByStudentId(@PathVariable Integer studentId) ;


}
