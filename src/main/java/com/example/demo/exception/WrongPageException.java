package com.example.demo.exception;

public class WrongPageException extends RuntimeException {
    public WrongPageException(String message) {
        super(message);
    }

}
