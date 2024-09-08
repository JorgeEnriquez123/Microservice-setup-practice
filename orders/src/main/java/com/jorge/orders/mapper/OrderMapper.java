package com.jorge.orders.mapper;

import com.jorge.orders.dto.CreateOrderDto;
import com.jorge.orders.dto.OrderDto;
import com.jorge.orders.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final OrderItemMapper orderItemMapper;

    public OrderDto toDto(Order order) {
        OrderDto orderDto = OrderDto.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .items(order.getItems().stream()
                        .map(orderItemMapper::toDto).collect(Collectors.toSet()))
                .build();
        return orderDto;
    }

    public Order toEntity(OrderDto orderDto) {
        Order order = Order.builder()
                .id(orderDto.getId())
                .customerId(orderDto.getCustomerId())
                .totalAmount(orderDto.getTotalAmount())
                .status(orderDto.getStatus())
                .items(orderDto.getItems().stream().map(orderItemMapper::toEntity).collect(Collectors.toSet()))
                .build();
        return order;
    }
}
