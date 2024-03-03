package com.example.rqchallenge.exception;

public class BadEmployeeRequest extends RuntimeException {
    private String message;

    public BadEmployeeRequest(String msg)
    {
        super(msg);
        this.message = msg;
    }
}