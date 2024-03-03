package com.example.rqchallenge.exception;

import com.example.rqchallenge.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleAllExceptions(Exception ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(new Response("failed",exceptionResponse), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchEmployeeExistsException.class)
    public final ResponseEntity<?> handleException(NoSuchEmployeeExistsException ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(new Response("failed",exceptionResponse), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public final ResponseEntity<?> handleException(EmployeeNotFoundException ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(new Response("failed",exceptionResponse), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadEmployeeRequest.class)
    public final ResponseEntity<?> handleException(BadEmployeeRequest ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(new Response("failed",exceptionResponse), HttpStatus.BAD_REQUEST);
    }
}
