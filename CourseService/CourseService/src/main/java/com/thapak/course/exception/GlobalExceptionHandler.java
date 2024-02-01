package com.thapak.course.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value =RuntimeException.class)
    public String runtimeEx(Exception exception){
        return "something went wrong !!!!";
    }
}
