package HW171.app;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200.0),
                new Product("Coffee Maker", "Appliances", 80.0),
                new Product("Headphones", "Electronics", 150.0),
                new Product("Blender", "Appliances", 50.0)
        );

        Map<String, Double> result = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.averagingDouble(Product::getPrice)));

        System.out.println("Результат: " + result);

        Optional<Map.Entry<String, Double>> maxCategory = result.entrySet().stream()
                .max(Map.Entry.comparingByValue());
        maxCategory.ifPresent(e ->
                System.out.println("Категорія з найвищою середньою ціною: " + e.getKey() + " (" + e.getValue() + ")"));
    }
}
