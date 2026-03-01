package HW182.app;

import java.util.Arrays;
import java.util.Optional;

public class ArrayProcessor {

    public Optional<Integer> findMax(int[] array) {
        int max;
        if (array.length == 0) {
            return Optional.empty();
        }
        max = Arrays.stream(array).max().orElse(0);
        return Optional.of(max);
    }

    public Optional<Integer> findMin(int[] array) {
        int min;
        if (array.length == 0) {
            return Optional.empty();
        }
        min = Arrays.stream(array).min().orElse(0);
        return Optional.of(min);
    }
}