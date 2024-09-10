package com.jorge.orders.feign.errordecoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jorge.orders.handler.exception.ProductNotFoundException;
import com.jorge.orders.handler.response.ErrorResponseDto;
import feign.Response;
import feign.codec.ErrorDecoder;
import io.micrometer.core.instrument.util.IOUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class InventoryErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Exception decode(String s, Response response) {
        /*ErrorResponseDto errorResponseDto = null;
        try {
            errorResponseDto = objectMapper.readValue(
                    (IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8)), ErrorResponseDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return switch (response.status()){
            case 404 -> new ProductNotFoundException(errorResponseDto);
            default -> errorDecoder.decode(s, response);
        };*/
        // Since Circuit Breaker's fallback is triggered for non 2xx code responses, which includes 404, I'm skipping this implementation.
        // This implementation is more granular since it check for each status code.
        return null;
    }
}
