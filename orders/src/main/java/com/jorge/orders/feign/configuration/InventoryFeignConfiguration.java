package com.jorge.orders.feign.configuration;

import com.jorge.orders.feign.errordecoder.InventoryErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryFeignConfiguration {
    @Bean
    public InventoryErrorDecoder errorDecoder() {
        return new InventoryErrorDecoder();
    }
}
