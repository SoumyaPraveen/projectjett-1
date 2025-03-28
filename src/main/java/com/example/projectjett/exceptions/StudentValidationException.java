package com.example.projectjett.exceptions;

public class StudentValidationException extends Exception {
    public StudentValidationException(String message) {
        super(message);
    }

    public StudentValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}
