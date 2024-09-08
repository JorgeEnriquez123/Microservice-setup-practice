package com.jorge.orders.dto;

import com.jorge.orders.model.OrderItem;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private Long customerId;
    private BigDecimal totalAmount;
    private String status;
    private Set<OrderItemsDto> items;
}
