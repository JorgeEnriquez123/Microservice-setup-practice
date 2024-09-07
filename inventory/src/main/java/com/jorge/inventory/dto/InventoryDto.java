package com.jorge.inventory.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryDto {
    private Long productId;
    private Integer quantity;
}
