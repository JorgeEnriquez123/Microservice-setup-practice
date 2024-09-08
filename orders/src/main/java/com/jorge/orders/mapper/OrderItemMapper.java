package com.jorge.orders.mapper;

import com.jorge.orders.dto.OrderItemsDto;
import com.jorge.orders.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    public OrderItemsDto toDto(OrderItem orderItem) {
        OrderItemsDto dto = OrderItemsDto.builder()
                .productId(orderItem.getProductId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .build();
        return dto;
    }

    public OrderItem toEntity(OrderItemsDto dto) {
        OrderItem orderItem = OrderItem.builder()
                .productId(dto.getProductId())
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .build();
        return orderItem;
    }
}
