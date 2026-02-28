package HW161.app;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.random.RandomGenerator;

public class Main {
    public static void main(String[] args) {
        MathOperation addition = new MathOperation() {
            @Override
            public int operate(int a, int b) {
                return a + b;
            }
        };
        System.out.println("Sum of two numbers: " + addition.operate(1, 2));

        StringManipulator toUpper = str -> str.toUpperCase();
        System.out.println("Uppercase conversion: " + toUpper.manipulate("SomeText"));

        Function<String, Integer> countUpper = StringListProcessor::countUppercase;
        System.out.println("Number of capital letters in a line: " + countUpper.apply("SomeText"));

        Supplier<Integer> random = () -> RandomNumberGenerator.generateRandomNumber(1, 100);
        System.out.println("Random number from 1 to 100: " + random.get());
    }
}
