package com.example.demo.exception;

public class WrongIdNumber extends RuntimeException {
    public WrongIdNumber(String message) {
        super(message);
    }
}
