package com.spring.exception;

import com.spring.exception.exceptions.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<?> exceptionHandler(BaseException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }
}
