package com.example.repository;

import com.example.model.Order;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class OrderRepository {

    private static final OrderRepository INSTANCE = new OrderRepository();

    private final Map<Long, Order> storage = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    private OrderRepository() {}

    public static OrderRepository getInstance() {
        return INSTANCE;
    }

    public Order save(Order order) {
        long id = idSequence.getAndIncrement();
        order.setId(id);
        storage.put(id, order);
        return order;
    }

    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public Collection<Order> findAll() {
        return storage.values();
    }

    public Optional<Order> update(Long id, Order updated) {
        if (!storage.containsKey(id)) {
            return Optional.empty();
        }
        updated.setId(id);
        storage.put(id, updated);
        return Optional.of(updated);
    }

    public boolean deleteById(Long id) {
        return storage.remove(id) != null;
    }

    public void clear() {
        storage.clear();
        idSequence.set(1);
    }
}
