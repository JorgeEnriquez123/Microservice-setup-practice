package com.jorge.orders.feign;

import com.jorge.orders.feign.configuration.InventoryFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory", configuration = InventoryFeignConfiguration.class)
public interface InventoryServiceClient {
    @GetMapping("/api/inventory/{productId}/check-availability")
    ResponseEntity<Boolean> checkAvailability(@PathVariable Long productId, @RequestParam Integer quantity);
}
