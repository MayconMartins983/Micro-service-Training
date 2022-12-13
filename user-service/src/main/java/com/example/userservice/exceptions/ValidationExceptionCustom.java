package com.example.userservice.exceptions;

public class ValidationExceptionCustom extends RuntimeException{
    public ValidationExceptionCustom(String message) {
        super(message);
    }
}
