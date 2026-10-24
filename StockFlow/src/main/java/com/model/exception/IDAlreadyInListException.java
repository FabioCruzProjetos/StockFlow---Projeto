package com.model.exception;

public class IDAlreadyInListException extends RuntimeException {
    public IDAlreadyInListException(String message) {
        super(message);
    }
}
