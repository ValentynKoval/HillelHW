package coffee.order;

import java.util.Objects;

public class Order {

    private final int number;
    private final String name;

    public Order(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return number == order.number && Objects.equals(name, order.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name);
    }

    @Override
    public String toString() {
        return number + " | " + name;
    }
}