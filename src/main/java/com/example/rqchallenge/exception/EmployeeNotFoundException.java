package com.example.rqchallenge.exception;

public class EmployeeNotFoundException extends RuntimeException {
    private String message;

    public EmployeeNotFoundException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
