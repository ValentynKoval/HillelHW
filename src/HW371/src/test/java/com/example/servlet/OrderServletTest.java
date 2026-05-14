package com.example.servlet;

import com.example.model.Order;
import com.example.model.Product;
import com.example.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServletTest {

    @Mock private OrderService orderService;
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;

    private OrderServlet servlet;
    private ObjectMapper objectMapper;
    private StringWriter responseWriter;

    @BeforeEach
    void setUp() throws Exception {
        servlet = new OrderServlet(orderService);

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        responseWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));
    }

    @Test
    @DisplayName("POST /orders – should create order and return 201")
    void doPost_shouldCreateOrderAndReturn201() throws Exception {
        Product p1 = new Product(null, "Laptop", new BigDecimal("999.99"));
        Order input = new Order(null, null, null, List.of(p1));
        Order saved = new Order(1L, LocalDateTime.now(), new BigDecimal("999.99"), List.of(p1));

        when(request.getInputStream())
                .thenReturn(new MockServletInputStream(objectMapper.writeValueAsBytes(input)));
        when(orderService.createOrder(any(Order.class))).thenReturn(saved);

        servlet.doPost(request, response);

        verify(response).setStatus(HttpServletResponse.SC_CREATED);
        verify(orderService).createOrder(any(Order.class));
        assertTrue(responseWriter.toString().contains("\"id\":1"));
    }

    @Test
    @DisplayName("GET /orders/1 – should return existing order with 200")
    void doGet_byId_shouldReturnOrderWhenFound() throws Exception {
        Order order = new Order(1L, LocalDateTime.now(), BigDecimal.TEN, List.of());
        when(request.getPathInfo()).thenReturn("/1");
        when(orderService.getOrder(1L)).thenReturn(Optional.of(order));

        servlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        assertTrue(responseWriter.toString().contains("\"id\":1"));
    }

    @Test
    @DisplayName("GET /orders/999 – should return 404 when order not found")
    void doGet_byId_shouldReturn404WhenNotFound() throws Exception {
        when(request.getPathInfo()).thenReturn("/999");
        when(orderService.getOrder(999L)).thenReturn(Optional.empty());

        servlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_NOT_FOUND);
        assertTrue(responseWriter.toString().contains("error"));
    }

    @Test
    @DisplayName("GET /orders – should return list of all orders with 200")
    void doGet_all_shouldReturnAllOrders() throws Exception {
        when(request.getPathInfo()).thenReturn(null);
        Order o1 = new Order(1L, LocalDateTime.now(), BigDecimal.TEN, List.of());
        Order o2 = new Order(2L, LocalDateTime.now(), BigDecimal.ONE, List.of());
        when(orderService.getAllOrders()).thenReturn(List.of(o1, o2));

        servlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        String body = responseWriter.toString();
        assertTrue(body.contains("\"id\":1"));
        assertTrue(body.contains("\"id\":2"));
    }

    @Test
    @DisplayName("PUT /orders/1 – should update order and return 200")
    void doPut_shouldUpdateOrderAndReturn200() throws Exception {
        Product p = new Product(1L, "Phone", new BigDecimal("499.00"));
        Order updated = new Order(1L, LocalDateTime.now(), new BigDecimal("499.00"), List.of(p));

        when(request.getPathInfo()).thenReturn("/1");
        when(request.getInputStream())
                .thenReturn(new MockServletInputStream(objectMapper.writeValueAsBytes(updated)));
        when(orderService.updateOrder(eq(1L), any(Order.class))).thenReturn(Optional.of(updated));

        servlet.doPut(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        assertTrue(responseWriter.toString().contains("\"id\":1"));
    }

    @Test
    @DisplayName("PUT /orders/999 – should return 404 when order not found")
    void doPut_shouldReturn404WhenOrderNotFound() throws Exception {
        Order updated = new Order(null, null, null, List.of());

        when(request.getPathInfo()).thenReturn("/999");
        when(request.getInputStream())
                .thenReturn(new MockServletInputStream(objectMapper.writeValueAsBytes(updated)));
        when(orderService.updateOrder(eq(999L), any(Order.class))).thenReturn(Optional.empty());

        servlet.doPut(request, response);

        verify(response).setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("PUT /orders (no id) – should return 400")
    void doPut_withoutId_shouldReturn400() throws Exception {
        when(request.getPathInfo()).thenReturn(null);

        servlet.doPut(request, response);

        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        verifyNoInteractions(orderService);
    }

    @Test
    @DisplayName("DELETE /orders/1 – should delete and return 204")
    void doDelete_shouldDeleteAndReturn204() throws Exception {
        when(request.getPathInfo()).thenReturn("/1");
        when(orderService.deleteOrder(1L)).thenReturn(true);

        servlet.doDelete(request, response);

        verify(response).setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("DELETE /orders/999 – should return 404 when not found")
    void doDelete_shouldReturn404WhenNotFound() throws Exception {
        when(request.getPathInfo()).thenReturn("/999");
        when(orderService.deleteOrder(999L)).thenReturn(false);

        servlet.doDelete(request, response);

        verify(response).setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("DELETE /orders (no id) – should return 400")
    void doDelete_withoutId_shouldReturn400() throws Exception {
        when(request.getPathInfo()).thenReturn(null);

        servlet.doDelete(request, response);

        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        verifyNoInteractions(orderService);
    }

    static class MockServletInputStream extends jakarta.servlet.ServletInputStream {
        private final ByteArrayInputStream delegate;

        MockServletInputStream(byte[] data) {
            this.delegate = new ByteArrayInputStream(data);
        }

        @Override public int read() { return delegate.read(); }
        @Override public boolean isFinished() { return delegate.available() == 0; }
        @Override public boolean isReady() { return true; }
        @Override public void setReadListener(jakarta.servlet.ReadListener l) {}
    }
}