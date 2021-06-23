package com.example.demo;

public class UnauthorizedMethodException extends RuntimeException{
    public UnauthorizedMethodException() {
    }

    public UnauthorizedMethodException(String message) {
        super(message);
    }
}
