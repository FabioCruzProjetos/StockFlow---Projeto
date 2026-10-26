package com.model.exception;

public class ProductDontExistException extends RuntimeException{

    public ProductDontExistException(String message) {
        super(message);
    }

}
