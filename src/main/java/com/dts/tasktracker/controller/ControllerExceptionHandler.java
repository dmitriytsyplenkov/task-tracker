package com.dts.tasktracker.controller;

import com.dts.tasktracker.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(Exception e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity handleNumberFormatException(Exception e) {
        return new ResponseEntity("Invalid id", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(Exception e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
