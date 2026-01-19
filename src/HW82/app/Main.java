package HW82.app;

public class Main {

    public static void main(String[] args) {
        DataProvider provider = new DataProvider();
        TemperatureValidator validator = new TemperatureValidator();
        try {
            getOutput(validator.validateTemperature(provider.getTemperature()));
        } catch (TemperatureException ex) {
            getOutput(ex.getMessage());
        }
    }

    private static void getOutput(String output) {
        System.out.println(output);
    }
}