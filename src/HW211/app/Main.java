package HW211.app;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayUtils arrayUtils = new ArrayUtils();
        int[] numbers = {4, 1, 8, 3, 2};

        System.out.println("Вихідний масив: " + Arrays.toString(numbers));
        System.out.println("Сума елементів: " + arrayUtils.sum(numbers));
        System.out.println("Максимальний елемент: " + arrayUtils.max(numbers));
        System.out.println("Масив у зворотному порядку: " + Arrays.toString(arrayUtils.reverse(numbers)));
        System.out.println();

        Method[] methods = ArrayUtils.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MethodInfo.class) && method.isAnnotationPresent(Author.class)) {
                MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);
                Author author = method.getAnnotation(Author.class);

                System.out.println("Інформація про метод:");
                System.out.println("Назва методу: " + methodInfo.name());
                System.out.println("Тип поверненого значення: " + methodInfo.returnType());
                System.out.println("Опис: " + methodInfo.description());
                System.out.println("Автор: " + author.firstName() + " " + author.lastName());
                System.out.println();
            }
        }
    }
}
