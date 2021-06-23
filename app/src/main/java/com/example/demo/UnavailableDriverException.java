package com.example.demo;

public class UnavailableDriverException extends RuntimeException{

    public UnavailableDriverException() {
    }

    public UnavailableDriverException(String mes) {
        super(mes);
    }


}
