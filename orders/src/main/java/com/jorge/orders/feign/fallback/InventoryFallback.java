package com.jorge.orders.feign.fallback;

import com.jorge.orders.feign.InventoryServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class InventoryFallback implements InventoryServiceClient {
    @Override
    public ResponseEntity<Boolean> checkAvailability(Long productId, Integer quantity) {
        return null;
    }
}
