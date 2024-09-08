package com.jorge.orders.service.impl;

import com.jorge.orders.dto.CreateOrderDto;
import com.jorge.orders.dto.OrderDto;
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
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    // hard-code products' ids and OrderItems will be saved with cascade
    @Transactional
    @Override
    public void createOrder(CreateOrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);

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

    @Transactional
    @Override
    public Order getInventory(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        return order;
    }

    @Transactional
    @Override
    public void updateOrderStatus(String status, Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if(order != null) {
            order.setStatus(status);
        }
        orderRepository.save(order);
    }
}
