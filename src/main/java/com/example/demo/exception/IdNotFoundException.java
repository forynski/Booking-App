package com.example.demo.exception;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(String id) {
        super(id + " does not exist");
    }
}
