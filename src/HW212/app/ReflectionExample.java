package HW212.app;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class ReflectionExample {

    public void analyzeClass(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);

        System.out.println("Інформація про клас: " + clazz.getName());

        Field[] fields = clazz.getDeclaredFields();
        if (fields.length > 0) {
            System.out.println("Поля класу:");
            for (Field field : fields) {
                System.out.println("\t" + field.getType().getSimpleName() + " " + field.getName());
            }
        } else {
            System.out.println("Клас не має полів.");
        }

        Method[] methods = clazz.getDeclaredMethods();
        if (methods.length > 0) {
            System.out.println("Методи класу:");
            for (Method method : methods) {
                System.out.println("\t" + method.getName() + Arrays.toString(method.getParameterTypes()));
            }
        } else {
            System.out.println("Клас не має методів.");
        }

        Annotation[] annotations = clazz.getAnnotations();
        if (annotations.length > 0) {
            System.out.println("Анотації класу:");
            for (Annotation annotation : annotations) {
                System.out.println("\t" + annotation.annotationType().getSimpleName());
            }
        } else {
            System.out.println("Клас не має анотацій.");
        }
    }

    public Object callMethod(String className, String methodName, Object... args)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName(className);
        Method method = findCompatibleMethod(clazz, methodName, args);

        if (method == null) {
            throw new NoSuchMethodException("Метод " + methodName + " з такими аргументами не знайдено");
        }

        method.setAccessible(true);
        Object instance = null;

        if (!Modifier.isStatic(method.getModifiers())) {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            instance = constructor.newInstance();
        }

        return method.invoke(instance, args);
    }

    private Method findCompatibleMethod(Class<?> clazz, String methodName, Object... args) {
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (!method.getName().equals(methodName)) {
                continue;
            }

            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != args.length) {
                continue;
            }

            boolean compatible = true;
            for (int i = 0; i < parameterTypes.length; i++) {
                if (!isArgumentCompatible(parameterTypes[i], args[i])) {
                    compatible = false;
                    break;
                }
            }

            if (compatible) {
                return method;
            }
        }

        return null;
    }

    private boolean isArgumentCompatible(Class<?> parameterType, Object arg) {
        if (arg == null) {
            return !parameterType.isPrimitive();
        }

        Class<?> argType = arg.getClass();

        if (parameterType.isPrimitive()) {
            if (parameterType == int.class) {
                return argType == Integer.class;
            }
            if (parameterType == long.class) {
                return argType == Long.class;
            }
            if (parameterType == double.class) {
                return argType == Double.class;
            }
            if (parameterType == float.class) {
                return argType == Float.class;
            }
            if (parameterType == boolean.class) {
                return argType == Boolean.class;
            }
            if (parameterType == char.class) {
                return argType == Character.class;
            }
            if (parameterType == short.class) {
                return argType == Short.class;
            }
            if (parameterType == byte.class) {
                return argType == Byte.class;
            }
            return false;
        }

        return parameterType.isAssignableFrom(argType);
    }

    public static void main(String[] args) {
        ReflectionExample reflectionExample = new ReflectionExample();

        try {
            reflectionExample.analyzeClass("java.lang.String");
            System.out.println();

            Object addResult = reflectionExample.callMethod("java.util.ArrayList", "add", "Java");
            System.out.println("Результат виклику ArrayList.add(\"Java\"): " + addResult);

            Object maxResult = reflectionExample.callMethod("java.lang.Math", "max", 10, 25);
            System.out.println("Результат виклику Math.max(10, 25): " + maxResult);

        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException | InstantiationException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
