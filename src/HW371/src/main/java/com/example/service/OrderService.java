package com.example.service;

import com.example.model.Order;
import com.example.repository.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order createOrder(Order order) {
        order.setDate(LocalDateTime.now());
        recalculateCost(order);
        return repository.save(order);
    }

    public Optional<Order> getOrder(Long id) {
        return repository.findById(id);
    }

    public Collection<Order> getAllOrders() {
        return repository.findAll();
    }

    public Optional<Order> updateOrder(Long id, Order updated) {
        recalculateCost(updated);
        return repository.update(id, updated);
    }

    public boolean deleteOrder(Long id) {
        return repository.deleteById(id);
    }

    private void recalculateCost(Order order) {
        if (order.getProducts() == null || order.getProducts().isEmpty()) {
            order.setCost(BigDecimal.ZERO);
            return;
        }
        BigDecimal total = order.getProducts().stream()
                .filter(p -> p.getCost() != null)
                .map(p -> p.getCost())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setCost(total);
    }
}
