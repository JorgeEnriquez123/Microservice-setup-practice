package com.jorge.inventory.handler;

import com.jorge.inventory.handler.exception.ProductInventoryNotFoundException;
import com.jorge.inventory.handler.response.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductInventoryNotFoundException.class)
    public ErrorResponseDto handleProductInventoryNotFoundException(ProductInventoryNotFoundException e) {
        return ErrorResponseDto.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("Product Inventory Not Found")
                .errors(Collections.singletonList(e.getMessage()))
                .build();
    }
}
