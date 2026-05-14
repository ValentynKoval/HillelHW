package com.example.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {

    private Long id;
    private LocalDateTime date;
    private BigDecimal cost;
    private List<Product> products;

    public Order() {
        this.products = new ArrayList<>();
    }

    public Order(Long id, LocalDateTime date, BigDecimal cost, List<Product> products) {
        this.id = id;
        this.date = date;
        this.cost = cost;
        this.products = products != null ? products : new ArrayList<>();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public BigDecimal getCost() { return cost; }
    public void setCost(BigDecimal cost) { this.cost = cost; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Order{id=" + id + ", date=" + date + ", cost=" + cost
                + ", products=" + products + "}";
    }
}
