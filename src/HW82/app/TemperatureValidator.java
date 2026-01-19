package HW82.app;

public class TemperatureValidator {

    public String validateTemperature(int temperature)
            throws TemperatureException{
        if (temperature >= -10 && temperature <= 35) {
            return "Temperature is in bounds.";
        } else {
            throw new TemperatureException("Invalid temperature.");
        }
    }
}