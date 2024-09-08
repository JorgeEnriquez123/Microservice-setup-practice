package com.jorge.orders.handler;

import com.jorge.orders.handler.exception.OrderNotFoundException;
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
    public ErrorResponseDto handleProductInventoryNotFoundException(OrderNotFoundException e) {
        return ErrorResponseDto.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("Order Not Found")
                .errors(Collections.singletonList(e.getMessage()))
                .build();
    }
}
