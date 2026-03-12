package HW211.app;

public class ArrayUtils {

    @MethodInfo(
            name = "sum",
            returnType = "int",
            description = "Повертає суму елементів масиву"
    )
    @Author(firstName = "Valentyn", lastName = "Koval")
    public int sum(int[] array) {
        int total = 0;
        for (int value : array) {
            total += value;
        }
        return total;
    }

    @MethodInfo(
            name = "max",
            returnType = "int",
            description = "Повертає найбільший елемент масиву"
    )
    @Author(firstName = "Valentyn", lastName = "Koval")
    public int max(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Масив не може бути порожнім");
        }

        int maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }
        return maxValue;
    }

    @MethodInfo(
            name = "reverse",
            returnType = "int[]",
            description = "Повертає новий масив у зворотному порядку"
    )
    @Author(firstName = "Valentyn", lastName = "Koval")
    public int[] reverse(int[] array) {
        int[] reversed = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            reversed[i] = array[array.length - 1 - i];
        }
        return reversed;
    }
}
