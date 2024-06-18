import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;

public class CalculateGrades {
    private static final String DB_URL = "jdbc:mysql://localhost:3308/GradingSystem";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC driver not found.", e);
        }

        return DriverManager.getConnection(DB_URL);
    }

//    public static void calculate() {
//        try (Connection conn = getConnection()) {
//            if (conn != null) {
//                System.out.println("Connected to the database!");
//
//                try (Statement stmt = conn.createStatement();
//                     Scanner sc = new Scanner(System.in)) {
//
//                    System.out.println("Enter name:");
//                    String name = sc.nextLine().trim();
//
//                    System.out.println("Enter Roll No.: ");
//                    int roll = Integer.parseInt(sc.nextLine().trim());
//
//                    String q = "SELECT email FROM studentList WHERE name= '" + name + "' AND roll_no= " + roll;
//
//                    ResultSet rs = stmt.executeQuery(q);
//
//                    if (rs.next()) {
//                        System.out.println("Enter marks in the sequence: ");
//                        System.out.println("DSA:");
//                        double dsa = sc.nextDouble();
//                        System.out.println("MMI:");
//                        double mmi = sc.nextDouble();
//                        System.out.println("NT_LAB:");
//                        double ntLab = sc.nextDouble();
//                        System.out.println("DBMS:");
//                        double dbms = sc.nextDouble();
//                        System.out.println("Cloud Computing:");
//                        double cloud = sc.nextDouble();
//
//                        double sum = dsa + mmi + ntLab + dbms + cloud;
//                        double avg = sum/5.0;
//                        String grade = "";
//
//                        if (avg >= 90 && avg<= 100) {
//                            grade = "O";
//                        } else if (avg >= 80 && avg < 90) {
//                            grade = "E";
//                        } else if (avg >= 70 && avg < 80) {
//                            grade = "A";
//                        } else if (avg >= 60 && avg < 70) {
//                            grade = "B";
//                        } else if (avg >= 50 && avg < 60) {
//                            grade = "C";
//                        } else if (avg >= 40 && avg < 50) {
//                            grade = "D";
//                        } else {
//                            grade = "F";
//                        }
//
//                        String query = "INSERT INTO grades (name, Roll_No,Subjects, DSA, MMI, NT_LAB, DBMS, Cloud_Computing, sum, grade_symbol) " +
//                                "VALUES ('" + name + "', " + roll +", " + 5 + ", " + dsa + ", " + mmi + ", " + ntLab + ", " + dbms + ", " + cloud + ", " +
//                                avg + ", '" + grade + "')";
//
//                        stmt.execute(query);
//                        System.out.println("Grades inserted successfully!");
//                    } else {
//                        System.out.println("Name doesn't exist in the student table");
//                    }
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            } else {
//                System.out.println("Failed to connect to the database.");
//            }
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
//        calculate();
    }
}
