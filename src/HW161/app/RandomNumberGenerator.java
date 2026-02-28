package HW161.app;

public class RandomNumberGenerator {
    public static int generateRandomNumber(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min must be less than or equal to max");
        }
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
