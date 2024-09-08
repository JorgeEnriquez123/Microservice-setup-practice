package com.jorge.orders.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderDto {
    private Long customerId;
    private Set<OrderItemsDto> items;
}
