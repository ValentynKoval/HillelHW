package HW82.app;

import java.util.Scanner;

public class DataProvider {

    public int getTemperature() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter temperature from -10 to 35: ");
        return scanner.nextInt();
    }
}