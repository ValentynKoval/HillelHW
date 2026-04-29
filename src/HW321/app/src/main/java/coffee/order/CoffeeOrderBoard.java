package coffee.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CoffeeOrderBoard {

    private static final Logger logger = LoggerFactory.getLogger(CoffeeOrderBoard.class);

    private final List<Order> orders = new LinkedList<>();
    private int nextOrderNumber = 1;

    public Order add(String name) {
        if (name == null || name.isBlank()) {
            IllegalArgumentException exception = new IllegalArgumentException("Customer name cannot be empty");
            logger.error("Failed to add new order. Invalid customer name: {}", name, exception);
            throw exception;
        }

        Order order = new Order(nextOrderNumber++, name);
        orders.add(order);

        logger.info("Added new order: number={}, name={}", order.getNumber(), order.getName());

        return order;
    }

    public Order deliver() {
        if (orders.isEmpty()) {
            IllegalStateException exception = new IllegalStateException("Order queue is empty");
            logger.error("Failed to deliver order. Queue is empty", exception);
            throw exception;
        }

        Order order = orders.remove(0);

        logger.info("Delivered nearest order: number={}, name={}", order.getNumber(), order.getName());

        return order;
    }

    public Order deliver(int orderNumber) {
        Optional<Order> foundOrder = orders.stream()
                .filter(order -> order.getNumber() == orderNumber)
                .findFirst();

        if (foundOrder.isEmpty()) {
            IllegalArgumentException exception = new IllegalArgumentException(
                    "Order with number " + orderNumber + " not found"
            );
            logger.error("Failed to deliver order by number={}. Order not found", orderNumber, exception);
            throw exception;
        }

        Order order = foundOrder.get();
        orders.remove(order);

        logger.info("Delivered order by number: number={}, name={}", order.getNumber(), order.getName());

        return order;
    }

    public void draw() {
        logger.debug("Drawing current order queue. Queue size={}", orders.size());

        System.out.println("Num | Name");

        for (Order order : orders) {
            System.out.println(order.getNumber() + " | " + order.getName());
        }
    }

    public List<Order> getOrders() {
        return List.copyOf(orders);
    }
}