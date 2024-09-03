package com.JPA.JPApractice.exception;

import com.JPA.JPApractice.Dao.StudentNotFoundException;
import com.JPA.JPApractice.Entity.StudentErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {

        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp(System.currentTimeMillis());
        error.setMessage(exc.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
        StudentErrorResponse error = new StudentErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                System.currentTimeMillis(),
                exc.getMessage()
        );

        // Return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
