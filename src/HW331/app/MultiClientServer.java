package HW331.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiClientServer {

    private static final int PORT = 8080;

    // Активні з'єднання: ім'я клієнта -> інформація про клієнта
    private static final Map<String, ClientConnection> ACTIVE_CONNECTIONS = new ConcurrentHashMap<>();

    // Лічильник для імен client-1, client-2, ...
    private static final AtomicInteger CLIENT_COUNTER = new AtomicInteger(1);

    public static void main(String[] args) {
        System.out.println("[SERVER] Сервер запущено на порту " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();

                String clientName = "client-" + CLIENT_COUNTER.getAndIncrement();
                ClientConnection clientConnection =
                        new ClientConnection(clientName, LocalDateTime.now(), clientSocket);

                ACTIVE_CONNECTIONS.put(clientName, clientConnection);

                System.out.println("[SERVER] " + clientName + " успішно підключився");

                Thread clientThread = new Thread(
                        new ClientHandler(clientConnection, ACTIVE_CONNECTIONS)
                );
                clientThread.start();
            }
        } catch (IOException e) {
            System.err.println("[SERVER] Помилка сервера: " + e.getMessage());
        }
    }
}