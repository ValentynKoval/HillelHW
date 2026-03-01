package HW172.app;

import java.util.List;

public class ProductProcessor {
    public double calculateTotalPrice(List<Product> products) {
        return products.stream()
                .filter(p -> p.getPrice() > 0)
                .mapToDouble(Product::getPrice)
                .sum();
    }
}