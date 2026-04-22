package HW241.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Logger {
    private static Logger instance;
    private final List<String> logs = new ArrayList<>();

    private Logger() {}

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        logs.add(message);
        System.out.println("[LOG] " + message);
    }

    public List<String> getLogs() {
        return Collections.unmodifiableList(logs);
    }

    public void printAllLogs() {
        System.out.println("\nУсі записи логування:");
        for (String log : logs) {
            System.out.println(log);
        }
    }
}
