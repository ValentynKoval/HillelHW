package com.example.service;

import com.example.model.Order;
import com.example.model.Product;
import com.example.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock private OrderRepository repository;
    @InjectMocks private OrderService service;

    private Order sampleOrder;

    @BeforeEach
    void setUp() {
        Product p = new Product(1L, "Book", new BigDecimal("20.00"));
        sampleOrder = new Order(null, null, null, List.of(p));
    }

    @Test
    @DisplayName("createOrder – should set date and calculate cost from products")
    void createOrder_shouldSetDateAndCalculateCost() {
        when(repository.save(any(Order.class))).thenAnswer(inv -> {
            Order o = inv.getArgument(0);
            o.setId(1L);
            return o;
        });

        Order result = service.createOrder(sampleOrder);

        assertNotNull(result.getDate(), "Date must be set");
        assertEquals(new BigDecimal("20.00"), result.getCost());
        verify(repository).save(any(Order.class));
    }

    @Test
    @DisplayName("getOrder – should delegate to repository")
    void getOrder_shouldDelegateToRepository() {
        Order stored = new Order(1L, null, BigDecimal.TEN, List.of());
        when(repository.findById(1L)).thenReturn(Optional.of(stored));

        Optional<Order> result = service.getOrder(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    @DisplayName("updateOrder – should recalculate cost and delegate to repository")
    void updateOrder_shouldRecalculateCostAndUpdate() {
        Product p = new Product(2L, "Pen", new BigDecimal("5.00"));
        Order updated = new Order(null, null, null, List.of(p));

        when(repository.update(eq(1L), any(Order.class)))
                .thenAnswer(inv -> Optional.of(inv.getArgument(1)));

        Optional<Order> result = service.updateOrder(1L, updated);

        assertTrue(result.isPresent());
        assertEquals(new BigDecimal("5.00"), result.get().getCost());
    }

    @Test
    @DisplayName("deleteOrder – returns true when repository removes order")
    void deleteOrder_shouldReturnTrueWhenDeleted() {
        when(repository.deleteById(1L)).thenReturn(true);

        assertTrue(service.deleteOrder(1L));
    }

    @Test
    @DisplayName("deleteOrder – returns false when order not found")
    void deleteOrder_shouldReturnFalseWhenNotFound() {
        when(repository.deleteById(99L)).thenReturn(false);

        assertFalse(service.deleteOrder(99L));
    }

    @Test
    @DisplayName("createOrder – cost is ZERO when products list is empty")
    void createOrder_shouldSetZeroCostForEmptyProducts() {
        Order empty = new Order(null, null, null, List.of());
        when(repository.save(any(Order.class))).thenAnswer(inv -> inv.getArgument(0));

        Order result = service.createOrder(empty);

        assertEquals(BigDecimal.ZERO, result.getCost());
    }
}
