import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3308/";
    private static final String DB_NAME = "GradingSystem";
    private static final String FULL_DB_URL = DB_URL + DB_NAME;

    private static final Scanner scanner = new Scanner(System.in);

    public static Connection getConnection(String url) throws SQLException {
        // Ensure the JDBC driver is loaded
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC driver not found.", e);
        }

        return DriverManager.getConnection(url);
    }

    public static void createDatabase(Connection connection) throws SQLException {
        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createDatabaseSQL);
            System.out.println("Database '" + DB_NAME + "' created successfully!");
        }
    }

    public static void createTable(Connection connection) throws SQLException {
        String createTableStudent = "CREATE TABLE IF NOT EXISTS studentList ("
                + "SNo INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(100) NOT NULL, "
                + "Roll_No INT NOT NULL, "
                + "email VARCHAR(100) NOT NULL UNIQUE"
                + ")";

        String createTableGrades = "CREATE TABLE IF NOT EXISTS grades ("
                + "SNo INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(100) NOT NULL, "
                + "Roll_No INT NOT NULL, "
                + "Subjects INT NOT NULL, "
                + "DSA DECIMAL(10,2) NOT NULL, "
                + "MMI DECIMAL(10,2) NOT NULL, "
                + "NT_LAB DECIMAL(10,2) NOT NULL, "
                + "DBMS DECIMAL(10,2) NOT NULL, "
                + "Cloud_Computing DECIMAL(10,2) NOT NULL,"
                + "Sum DECIMAL(10,2) not null,"
                + "Grade_symbol Varchar(2) not null"
                + ")";

        String gradeTable = "CREATE TABLE IF NOT EXISTS gradeTable ("
                + "SNo INT AUTO_INCREMENT PRIMARY KEY, " // Add primary key
                + "Score_Range VARCHAR(30), "
                + "Grade_Symbol VARCHAR(2)"
                + ")";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableStudent);
            stmt.execute(createTableGrades);
            stmt.execute(gradeTable);
            System.out.println("Tables 'studentList', 'grades', and 'gradeTable' created successfully!");
        }
    }

    public static void delete(Connection connection, String tableName) throws SQLException {
        System.out.println("Deleting table content");
        String truncateQuery = "TRUNCATE TABLE " + tableName;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(truncateQuery);
            System.out.println("Table content deleted successfully");
        } catch (SQLException e) {
            System.err.println("Error deleting table content: " + e.getMessage());
            throw e; // rethrow the exception after logging it
        }
    }

    public static void insertData(Connection connection) throws SQLException {
//        System.out.println("Enter name:");
//        String name = scanner.nextLine().trim();
//
//        System.out.println("Enter Roll No.: ");
//        int roll = Integer.parseInt(scanner.nextLine().trim());
//
//        System.out.println("Enter email:");
//        String email = scanner.nextLine().trim();
//
//
//        String insertDataStudent = "INSERT INTO studentList (name, Roll_No, email) VALUES " +
//                    "('" + name + "', " + roll + ", '" + email + "')";

        String insertGradeTable = "INSERT INTO gradeTable (Score_range, grade_symbol) VALUES " +
                "('90-100', 'O')," +
                "('80-89', 'E')," +
                "('70-79', 'A')," +
                "('60-69', 'B')," +
                "('50-59', 'C')," +
                "('40-49', 'D')," +
                "('0-40', 'F')";

        try (Statement stmt = connection.createStatement()) {
//            stmt.executeUpdate(insertDataStudent);
            stmt.executeUpdate(insertGradeTable);
            System.out.println("Data inserted successfully");
        }
    }

    public static void create() {
        try (Connection serverConnection = getConnection(DB_URL)) {
            if (serverConnection != null) {
                System.out.println("Connected to the MySQL server!");

                // Step 2: Create the database
                createDatabase(serverConnection);
            } else {
                System.out.println("Failed to connect to the MySQL server.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Step 3: Connect to the newly created database
        try (Connection dbConnection = getConnection(FULL_DB_URL)) {
            if (dbConnection != null) {
                System.out.println("Connected to the database '" + DB_NAME + "'!");

                // Step 4: Create the 'studentList' and 'grades' tables
//                createTable(dbConnection);

                // Insert values
//                insertData(dbConnection);

                delete(dbConnection, "grades");

            } else {
                System.out.println("Failed to connect to the database '" + DB_NAME + "'.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close(); // Close the scanner
        }
    }

    public static void main(String[] args) {
        create();
    }
}
