package com.jorge.orders.handler.exception;

public class CantCheckProductException extends RuntimeException{
    public CantCheckProductException(String message) {
        super(message);
    }
}
