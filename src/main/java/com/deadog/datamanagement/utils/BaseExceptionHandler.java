package com.deadog.datamanagement.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public void handle() {
        throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Такой элемент не найден");
    }
}
