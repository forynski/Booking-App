package com.example.demo.Error;

public class WrongIdNumber extends RuntimeException {
    public WrongIdNumber(String message) {
        super(message);
    }
}
