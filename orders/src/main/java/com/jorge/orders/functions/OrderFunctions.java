package com.jorge.orders.functions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class OrderFunctions {

    @Bean
    public Consumer<Long> orderConfirmation(){
        return orderId -> {
            log.info("Order with ID: {} confirmed. Email and SMS has been successfully sent", orderId);
        };
    }
}
