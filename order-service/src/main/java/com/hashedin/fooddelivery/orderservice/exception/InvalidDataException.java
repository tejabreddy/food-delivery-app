package com.hashedin.fooddelivery.orderservice.exception;

public class InvalidDataException extends RuntimeException{

    public InvalidDataException(String errorMessage) {
        super(errorMessage);
    }
}
