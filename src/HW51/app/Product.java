package HW51.app;

public class Product {
    private final String name;
    private final int quota;
    private final double price;

    public Product(String name, int quota, double price) {
        this.name = name;
        this.quota = quota;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuota() {
        return quota;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product: " + name +
                ", quota is " + quota + " " +
                Constants.MEASURE +
                ", price is " + Constants.CURRENCY +
                " " + price + ".";
    }
}
