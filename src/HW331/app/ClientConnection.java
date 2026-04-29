package HW331.app;

import java.net.Socket;
import java.time.LocalDateTime;

public class ClientConnection {
    private final String clientName;
    private final LocalDateTime connectedAt;
    private final Socket socket;

    public ClientConnection(String clientName, LocalDateTime connectedAt, Socket socket) {
        this.clientName = clientName;
        this.connectedAt = connectedAt;
        this.socket = socket;
    }

    public String getClientName() {
        return clientName;
    }

    public LocalDateTime getConnectedAt() {
        return connectedAt;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public String toString() {
        return "ClientConnection{" +
                "clientName='" + clientName + '\'' +
                ", connectedAt=" + connectedAt +
                ", socket=" + socket +
                '}';
    }
}