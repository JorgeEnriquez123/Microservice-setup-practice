package com.jorge.orders.handler;

import com.jorge.orders.handler.exception.CantCheckProductException;
import com.jorge.orders.handler.exception.OrderNotFoundException;
import com.jorge.orders.handler.exception.ProductNotAvailableException;
import com.jorge.orders.handler.exception.ProductNotFoundException;
import com.jorge.orders.handler.response.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderNotFoundException.class)
    public ErrorResponseDto handleOrderNotFoundException(OrderNotFoundException e) {
        return ErrorResponseDto.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("Order Not Found")
                .errors(Collections.singletonList(e.getMessage()))
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorResponseDto handleProductNotFoundException(ProductNotFoundException e) {
        return e.getErrorResponseDto();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CantCheckProductException.class)
    public ErrorResponseDto handleCantCheckProductException(CantCheckProductException e) {
        return ErrorResponseDto.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("Can't Check Product Availability")
                .errors(Collections.singletonList(e.getMessage()))
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotAvailableException.class)
    public ErrorResponseDto handleProductNotAvailableException(ProductNotAvailableException e) {
        return ErrorResponseDto.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("Product not available")
                .errors(Collections.singletonList(e.getMessage()))
                .build();
    }
}
