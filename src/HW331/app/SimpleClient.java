package HW331.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleClient {

    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket(HOST, PORT);
                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
                BufferedReader serverReader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter serverWriter = new PrintWriter(socket.getOutputStream(), true)
        ) {
            System.out.println(serverReader.readLine());
            System.out.println(serverReader.readLine());

            String userInput;
            while (true) {
                System.out.print("Введіть команду: ");
                userInput = consoleReader.readLine();

                if (userInput == null) {
                    break;
                }

                serverWriter.println(userInput);

                String serverResponse = serverReader.readLine();
                if (serverResponse != null) {
                    System.out.println(serverResponse);
                }

                if ("exit".equalsIgnoreCase(userInput.trim())) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("[CLIENT] Помилка клієнта: " + e.getMessage());
        }
    }
}