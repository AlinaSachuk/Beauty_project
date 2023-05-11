package com.beauty_project.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;

@RestControllerAdvice
public class ExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(ExceptionResolver.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HttpStatus> handleException(NotFoundException e) {
        log.warn(e.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<HttpStatus> handleException(EmptyResultDataAccessException e) {
        log.warn(e.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<HttpStatus> handleException(ArithmeticException e) {
        log.warn(e.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorizationServiceException.class)
    public ResponseEntity<HttpStatus> handleException(AuthorizationServiceException e) {
        log.warn(e.getMessage());
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpStatus> handleException(Exception e) {
        log.warn(e.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}