package com.jorge.orders.service.impl;

import com.jorge.orders.dto.CreateOrderDto;
import com.jorge.orders.dto.OrderItemsDto;
import com.jorge.orders.feign.InventoryServiceClient;
import com.jorge.orders.handler.exception.CantCheckProductException;
import com.jorge.orders.handler.exception.OrderNotFoundException;
import com.jorge.orders.handler.exception.ProductNotAvailableException;
import com.jorge.orders.handler.exception.ProductNotFoundException;
import com.jorge.orders.mapper.OrderItemMapper;
import com.jorge.orders.model.Order;
import com.jorge.orders.model.OrderItem;
import com.jorge.orders.repository.OrderRepository;
import com.jorge.orders.service.IOrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final OrderItemMapper orderItemMapper;
    private final InventoryServiceClient inventoryClient;

    // hard-code products' ids and OrderItems will be saved with cascade
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createOrder(CreateOrderDto orderDto) {
        for(OrderItemsDto item : orderDto.getItems()) {
            ResponseEntity<Boolean> isAvailable = inventoryClient.checkAvailability(item.getProductId(), item.getQuantity());
            if(null == isAvailable){
                log.debug("the product ID does not exist or the service is unreachable.");
                throw new CantCheckProductException("Product's availability checking was not successful. Try again later.");
            }
            if(Boolean.FALSE.equals(isAvailable.getBody())) {
                throw new ProductNotAvailableException("One of the products is not available due to low stock. Product ID: " + item.getProductId());
            }
        }

        Order order = Order.builder()
                .customerId(orderDto.getCustomerId())
                .build();

        BigDecimal total = calculateTotal(orderDto.getItems());
        order.setTotalAmount(total);
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());

        Set<OrderItem> orderItems = orderDto.getItems().
                stream().map(orderItemMapper::toEntity).collect(Collectors.toSet());
        orderItems.forEach(item -> item.setOrder(order));

        order.setItems(orderItems);
        orderRepository.save(order);

        //TODO Update Inventory with event driven mechanism
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

    public BigDecimal calculateTotal(Set<OrderItemsDto> items){
        return items.stream().map(
                item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
                ).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
