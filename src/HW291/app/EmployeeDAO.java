package HW291.app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private final DatabaseConnector connector;

    public EmployeeDAO(DatabaseConnector connector) {
        this.connector = connector;
    }

    public void createTableIfNotExists() throws SQLException {
        String createTableSql = "CREATE TABLE IF NOT EXISTS employees (" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(255)," +
                "age INTEGER," +
                "position VARCHAR(255)," +
                "salary FLOAT" +
                ")";

        connector.executeUpdate(createTableSql);
    }

    public void addEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees(name, age, position, salary) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connector.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, employee.getName());
            statement.setInt(2, employee.getAge());
            statement.setString(3, employee.getPosition());
            statement.setFloat(4, employee.getSalary());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    employee.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public boolean updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE employees SET name = ?, age = ?, position = ?, salary = ? WHERE id = ?";

        try (PreparedStatement statement = connector.getConnection().prepareStatement(sql)) {
            statement.setString(1, employee.getName());
            statement.setInt(2, employee.getAge());
            statement.setString(3, employee.getPosition());
            statement.setFloat(4, employee.getSalary());
            statement.setInt(5, employee.getId());

            return statement.executeUpdate() > 0;
        }
    }

    public boolean deleteEmployee(int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id = ?";

        try (PreparedStatement statement = connector.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    public Employee getEmployeeById(int id) throws SQLException {
        String sql = "SELECT id, name, age, position, salary FROM employees WHERE id = ?";

        try (PreparedStatement statement = connector.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRow(resultSet);
                }
            }
        }

        return null;
    }

    public List<Employee> getAllEmployees() throws SQLException {
        String sql = "SELECT id, name, age, position, salary FROM employees ORDER BY id";
        List<Employee> employees = new ArrayList<Employee>();

        try (PreparedStatement statement = connector.getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                employees.add(mapRow(resultSet));
            }
        }

        return employees;
    }

    private Employee mapRow(ResultSet resultSet) throws SQLException {
        return new Employee(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("age"),
                resultSet.getString("position"),
                resultSet.getFloat("salary")
        );
    }
}
