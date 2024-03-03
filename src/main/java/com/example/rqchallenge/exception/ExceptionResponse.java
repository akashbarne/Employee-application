package com.example.rqchallenge.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class ExceptionResponse {
    Date timestamp;
    String message;
    String details;
}
