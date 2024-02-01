package com.thapak.student.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String runtimeEx(Exception exception){
        log.info(exception.getMessage());
        return "something went wrong !!!!!!!!";
    }
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerEx(Exception exception){
        log.info(exception.getMessage());
        return "oops !!!!!!!!";
    }
}
