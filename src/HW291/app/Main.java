package HW291.app;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String host = System.getenv().getOrDefault("DB_HOST", "localhost");
        String port = System.getenv().getOrDefault("DB_PORT", "5432");
        String dbName = "company";
        String dbUser = System.getenv().getOrDefault("DB_USER", "postgres");
        String dbPassword = System.getenv().getOrDefault("DB_PASSWORD", "postgres");

        String adminUrl = "jdbc:postgresql://" + host + ":" + port + "/postgres";
        String companyUrl = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;

        DatabaseConnector connector = new DatabaseConnector(adminUrl, companyUrl, dbUser, dbPassword);

        try {
            connector.createDatabaseIfNotExists(dbName);
            connector.connect();

            EmployeeDAO employeeDAO = new EmployeeDAO(connector);
            employeeDAO.createTableIfNotExists();

            Employee firstEmployee = new Employee("John Smith", 30, "Developer", 3000f);
            Employee secondEmployee = new Employee("Alice Brown", 27, "QA Engineer", 2500f);

            employeeDAO.addEmployee(firstEmployee);
            employeeDAO.addEmployee(secondEmployee);

            System.out.println("After insertion:");
            printEmployees(employeeDAO.getAllEmployees());

            secondEmployee.setPosition("Senior QA Engineer");
            secondEmployee.setSalary(3000f);
            employeeDAO.updateEmployee(secondEmployee);

            System.out.println();
            System.out.println("After update:");
            System.out.println(employeeDAO.getEmployeeById(secondEmployee.getId()));

            employeeDAO.deleteEmployee(firstEmployee.getId());

            System.out.println();
            System.out.println("After deletion:");
            printEmployees(employeeDAO.getAllEmployees());
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } finally {
            connector.closeConnection();
        }
    }

    private static void printEmployees(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}
