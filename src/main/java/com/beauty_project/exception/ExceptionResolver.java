package com.beauty_project.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webjars.NotFoundException;

@ControllerAdvice
public class ExceptionResolver {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HttpStatus> notFound(Exception e) {
        log.warn("NotFoundException: " + e.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<HttpStatus> arithmeticException(Exception e) {
        log.warn("ArithmeticException: " + e.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
