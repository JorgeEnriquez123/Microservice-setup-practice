package com.jorge.inventory.mapper;

import com.jorge.inventory.model.Inventory;
import com.jorge.inventory.dto.InventoryDto;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {
    public InventoryDto toDto(Inventory inventory) {
        return InventoryDto.builder()
                .productId(inventory.getProductId())
                .quantity(inventory.getQuantity())
                .build();
    }

    public Inventory toEntity(InventoryDto dto) {
        return Inventory.builder()
                .productId(dto.getProductId())
                .quantity(dto.getQuantity())
                .build();
    }
}
