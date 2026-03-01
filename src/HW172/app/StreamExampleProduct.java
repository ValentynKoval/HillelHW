package HW172.app;

import java.util.Arrays;
import java.util.List;

public class StreamExampleProduct {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", 1200.0),
                new Product("Phone", 500.0),
                new Product("Headphones", 80.0),
                new Product("Tablet", 300.0),
                new Product("Camera", 700.0)
        );

        ProductFilter priceFilter = new PriceFilter(500.0);
        List<Product> filteredProducts = priceFilter.filter(products);

        System.out.println("Фільтрація за ціною:");
        filteredProducts.forEach(System.out::println);

        ProductProcessor productProcessor = new ProductProcessor();
        double total = productProcessor.calculateTotalPrice(products);

        System.out.println("\nЗагальна вартість: $" + total);
    }
}