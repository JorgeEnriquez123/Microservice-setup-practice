package com.jorge.inventory.handler.exception;

public class ProductInventoryNotFoundException extends RuntimeException{

    public ProductInventoryNotFoundException(String message) {
        super(message);
    }
}
