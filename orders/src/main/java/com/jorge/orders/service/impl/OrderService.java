package com.jorge.orders.service.impl;

import com.jorge.orders.dto.CreateOrderDto;
import com.jorge.orders.dto.OrderDto;
import com.jorge.orders.handler.exception.OrderNotFoundException;
import com.jorge.orders.mapper.OrderItemMapper;
import com.jorge.orders.mapper.OrderMapper;
import com.jorge.orders.model.Order;
import com.jorge.orders.model.OrderItem;
import com.jorge.orders.repository.OrderRepository;
import com.jorge.orders.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final OrderItemMapper orderItemMapper;

    // hard-code products' ids and OrderItems will be saved with cascade
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createOrder(CreateOrderDto orderDto) {
        Order order = Order.builder()
                .customerId(orderDto.getCustomerId())
                .build();

        BigDecimal total = orderDto.getItems().stream().map(
            item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
        ).reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalAmount(total);
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());

        Set<OrderItem> orderItems = orderDto.getItems().
                stream().map(orderItemMapper::toEntity).collect(Collectors.toSet());
        orderItems.forEach(item -> item.setOrder(order));

        order.setItems(orderItems);
        orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException("Order not found with id: " + orderId));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateOrderStatus(String status, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException("Order not found with id: " + orderId));
        order.setStatus(status);
        orderRepository.save(order);
    }
}
