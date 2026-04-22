package HW241.app;

public class Main {
    public static void main(String[] args) {
        Logger loggerFromMain = Logger.getInstance();
        loggerFromMain.log("Старт програми.");

        UserModule userModule = new UserModule();
        PaymentModule paymentModule = new PaymentModule();

        userModule.registerUser("Olena");
        paymentModule.processPayment(2500.0);

        Logger loggerFromMainAgain = Logger.getInstance();

        System.out.println("\nПеревірка Singleton:");
        System.out.println("loggerFromMain == loggerFromMainAgain: " + (loggerFromMain == loggerFromMainAgain));
        System.out.println("loggerFromMain == userModule.getLogger(): " + (loggerFromMain == userModule.getLogger()));
        System.out.println("loggerFromMain == paymentModule.getLogger(): " + (loggerFromMain == paymentModule.getLogger()));

        loggerFromMain.log("Завершення програми.");

        loggerFromMain.printAllLogs();
        System.out.println("\nКількість записів у логері: " + loggerFromMain.getLogs().size());
    }
}

class UserModule {
    private final Logger logger = Logger.getInstance();

    public void registerUser(String userName) {
        logger.log("UserModule: зареєстровано користувача " + userName + ".");
    }

    public Logger getLogger() {
        return logger;
    }
}

class PaymentModule {
    private final Logger logger = Logger.getInstance();

    public void processPayment(double amount) {
        logger.log("PaymentModule: оброблено платіж на суму " + amount + ".");
    }

    public Logger getLogger() {
        return logger;
    }
}
