package com.jorge.orders.controller;

import com.jorge.orders.dto.CreateOrderDto;
import com.jorge.orders.dto.OrderDto;
import com.jorge.orders.model.Order;
import com.jorge.orders.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderDto orderDto) {
        orderService.createOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        Order order = orderService.getInventory(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Long orderId,
                                                  @RequestParam String status) {
        orderService.updateOrderStatus(status, orderId);
        return ResponseEntity.ok().build();
    }
}
