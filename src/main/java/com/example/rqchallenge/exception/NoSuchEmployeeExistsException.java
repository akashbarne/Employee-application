package com.example.rqchallenge.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoSuchEmployeeExistsException extends RuntimeException{
    private String message;

    public NoSuchEmployeeExistsException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}