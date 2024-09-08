package com.jorge.orders.dto;

import com.jorge.orders.model.Order;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemsDto {
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
}
