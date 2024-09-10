package com.jorge.orders.feign;

import com.jorge.orders.feign.configuration.InventoryFeignConfiguration;
import com.jorge.orders.feign.fallback.InventoryFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Primary
@FeignClient(name = "inventory", configuration = InventoryFeignConfiguration.class, fallback = InventoryFallback.class)
public interface InventoryServiceClient {
    @GetMapping("/api/inventory/{productId}/check-availability")
    ResponseEntity<Boolean> checkAvailability(@PathVariable Long productId, @RequestParam Integer quantity);
}
