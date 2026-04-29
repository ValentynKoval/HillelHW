package HW331.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

public class ClientHandler implements Runnable {

    private final ClientConnection clientConnection;
    private final Map<String, ClientConnection> activeConnections;

    public ClientHandler(ClientConnection clientConnection,
                         Map<String, ClientConnection> activeConnections) {
        this.clientConnection = clientConnection;
        this.activeConnections = activeConnections;
    }

    @Override
    public void run() {
        Socket socket = clientConnection.getSocket();
        String clientName = clientConnection.getClientName();

        try (
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true)
        ) {
            output.println("[SERVER] Ви підключені як " + clientName);
            output.println("[SERVER] Для відключення введіть команду: exit");

            String message;
            while ((message = input.readLine()) != null) {
                System.out.println("[SERVER] Отримано від " + clientName + ": " + message);

                if ("exit".equalsIgnoreCase(message.trim())) {
                    output.println("[SERVER] Ви були відключені від сервера");
                    disconnectClient(clientName);
                    break;
                } else {
                    output.println("[SERVER] Команду отримано: " + message);
                }
            }
        } catch (IOException e) {
            System.out.println("[SERVER] Помилка роботи з " + clientName + ": " + e.getMessage());
            disconnectClient(clientName);
        }
    }

    private void disconnectClient(String clientName) {
        ClientConnection removedClient = activeConnections.remove(clientName);

        if (removedClient != null) {
            try {
                removedClient.getSocket().close();
            } catch (IOException e) {
                System.out.println("[SERVER] Не вдалося закрити сокет " + clientName);
            }

            System.out.println("[SERVER] " + clientName + " відключився");
        }
    }
}