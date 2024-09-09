package com.jorge.orders.handler.exception;

import com.jorge.orders.handler.response.ErrorResponseDto;
import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException{
    private ErrorResponseDto errorResponseDto;

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(ErrorResponseDto errorResponseDto) {
        this.errorResponseDto = errorResponseDto;
    }

}
