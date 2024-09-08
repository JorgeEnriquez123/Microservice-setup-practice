package com.jorge.orders.service;

import com.jorge.orders.dto.CreateOrderDto;
import com.jorge.orders.model.Order;

public interface IOrderService {
    void createOrder(CreateOrderDto orderDto);
    Order getOrder(Long orderId);
    void updateOrderStatus(String status, Long orderId);
}
