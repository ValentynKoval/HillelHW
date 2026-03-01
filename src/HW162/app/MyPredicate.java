package HW162.app;

import java.util.function.Predicate;

@FunctionalInterface
public interface MyPredicate<T> extends Predicate<T> {

    @Override
    boolean test(T t);

    @Override
    default MyPredicate<T> negate() {
        return t -> !test(t);
    }
}
