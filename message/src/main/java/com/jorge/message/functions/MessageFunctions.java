package com.jorge.message.functions;

import com.jorge.message.dto.OrderMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Slf4j
@Configuration
public class MessageFunctions {

    @Bean
    public Function<OrderMessageDto, OrderMessageDto> email() {
        return orderMessageDto -> {
            log.info("Sending Email with Order details: {}", orderMessageDto);
            log.info("Email Sent.");
            return orderMessageDto;
        };
    }

    @Bean
    public Function<OrderMessageDto, Long> sms() {
        return orderMessageDto -> {
            log.info("Sending Sms with Order details: {}", orderMessageDto);
            log.info("SMS Sent.");
            return orderMessageDto.orderId();
        };
    }
}
