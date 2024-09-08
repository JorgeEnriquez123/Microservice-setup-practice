package com.jorge.inventory.handler.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseDto {
    private HttpStatus httpStatus;
    private String message;
    private List<String> errors;
}
