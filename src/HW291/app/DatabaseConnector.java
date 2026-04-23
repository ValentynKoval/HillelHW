package HW291.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    private final String adminUrl;
    private final String companyUrl;
    private final String user;
    private final String password;
    private Connection connection;

    public DatabaseConnector(String adminUrl, String companyUrl, String user, String password) {
        this.adminUrl = adminUrl;
        this.companyUrl = companyUrl;
        this.user = user;
        this.password = password;
    }

    public void createDatabaseIfNotExists(String databaseName) throws SQLException {
        String existsSql = "SELECT 1 FROM pg_database WHERE datname = ?";

        try (Connection adminConnection = DriverManager.getConnection(adminUrl, user, password);
             PreparedStatement existsStatement = adminConnection.prepareStatement(existsSql)) {

            existsStatement.setString(1, databaseName);
            try (ResultSet resultSet = existsStatement.executeQuery()) {
                if (!resultSet.next()) {
                    try (Statement createStatement = adminConnection.createStatement()) {
                        createStatement.executeUpdate("CREATE DATABASE " + databaseName);
                    }
                }
            }
        }
    }

    public void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(companyUrl, user, password);
        }
    }

    public Connection getConnection() throws SQLException {
        connect();
        return connection;
    }

    public int executeUpdate(String sql) throws SQLException {
        try (Statement statement = getConnection().createStatement()) {
            return statement.executeUpdate(sql);
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ignored) {
                // Nothing else to do on shutdown.
            }
        }
    }
}
