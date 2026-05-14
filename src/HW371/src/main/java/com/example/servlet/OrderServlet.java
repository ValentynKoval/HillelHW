package com.example.servlet;

import com.example.model.Order;
import com.example.repository.OrderRepository;
import com.example.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "OrderServlet", urlPatterns = "/orders/*")
public class OrderServlet extends HttpServlet {

    private OrderService orderService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        this.orderService = new OrderService(OrderRepository.getInstance());
        this.objectMapper = buildObjectMapper();
    }

    OrderServlet(OrderService orderService) {
        this.orderService = orderService;
        this.objectMapper = buildObjectMapper();
    }

    public OrderServlet() {}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Order order = objectMapper.readValue(req.getInputStream(), Order.class);
        Order created = orderService.createOrder(order);

        writeJson(resp, created, HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Long id = extractId(req);

        if (id == null) {
            writeJson(resp, orderService.getAllOrders(), HttpServletResponse.SC_OK);
            return;
        }

        Optional<Order> found = orderService.getOrder(id);
        if (found.isPresent()) {
            writeJson(resp, found.get(), HttpServletResponse.SC_OK);
        } else {
            sendError(resp, HttpServletResponse.SC_NOT_FOUND,
                    "Order not found: " + id);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Long id = extractId(req);
        if (id == null) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST,
                    "Order id is required for update");
            return;
        }

        Order updated = objectMapper.readValue(req.getInputStream(), Order.class);
        Optional<Order> result = orderService.updateOrder(id, updated);

        if (result.isPresent()) {
            writeJson(resp, result.get(), HttpServletResponse.SC_OK);
        } else {
            sendError(resp, HttpServletResponse.SC_NOT_FOUND,
                    "Order not found: " + id);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Long id = extractId(req);
        if (id == null) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST,
                    "Order id is required for delete");
            return;
        }

        boolean deleted = orderService.deleteOrder(id);
        if (deleted) {
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            sendError(resp, HttpServletResponse.SC_NOT_FOUND,
                    "Order not found: " + id);
        }
    }

    private Long extractId(HttpServletRequest req) {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            return null;
        }
        try {
            return Long.parseLong(pathInfo.substring(1));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void writeJson(HttpServletResponse resp, Object body, int status)
            throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(status);
        objectMapper.writeValue(resp.getWriter(), body);
    }

    private void sendError(HttpServletResponse resp, int status, String message)
            throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(status);
        resp.getWriter().write("{\"error\":\"" + message + "\"}");
    }

    private static ObjectMapper buildObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
}
