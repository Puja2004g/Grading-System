import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class GradesFrame implements ActionListener {
    JFrame frameStudent, frameGrade;
    JTextField DSA, MMI, DBMS, NT_LAB, CLOUD, name, Roll, email, nameG, rollG;
    JLabel LDSA, LMMI, LDBMS, LNT_LAB, LCLOUD, Lname, LRoll, Lemail, Lnameg, Lrollg;
    JButton Add, addGrade, clear, clearGrade;

    GradesFrame() {
        frameStudent = new JFrame("Student table");
        frameGrade = new JFrame("Add marks");

        frameStudent.setSize(400, 400);
        frameStudent.setLayout(null);
        frameStudent.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frameGrade.setSize(400, 600);
        frameGrade.setLayout(null);
        frameGrade.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // frame for student list

        // name
        Lname = new JLabel("Name: ");
        Lname.setBounds(50, 50, 120, 25);
        frameStudent.add(Lname);

        name = new JTextField();
        name.setBounds(120, 50, 120, 25);
        frameStudent.add(name);

        // roll
        LRoll = new JLabel("Roll no. : ");
        LRoll.setBounds(50, 100, 130, 25);
        frameStudent.add(LRoll);

        Roll = new JTextField();
        Roll.setBounds(120, 100, 120, 25);
        frameStudent.add(Roll);

        // email id
        Lemail = new JLabel("Email Id: ");
        Lemail.setBounds(50, 150, 130, 25);
        frameStudent.add(Lemail);

        email = new JTextField();
        email.setBounds(120, 150, 120, 25);
        frameStudent.add(email);

        // add button
        Add = new JButton("Add");
        Add.setBounds(50, 230, 70, 25);
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        frameStudent.add(Add);

        clear = new JButton("Clear");
        clear.setBounds(130, 230, 70, 25);
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                Roll.setText("");
                email.setText("");
            }
        });
        frameStudent.add(clear);

//        frameStudent.setVisible(true);

        // frame grade

        // name
        Lnameg = new JLabel("Name: ");
        Lnameg.setBounds(50, 50, 120, 25);
        frameGrade.add(Lnameg);

        nameG = new JTextField();
        nameG.setBounds(120, 50, 120, 25);
        frameGrade.add(nameG);

        // roll
        Lrollg = new JLabel("Roll no. : ");
        Lrollg.setBounds(50, 100, 130, 25);
        frameGrade.add(Lrollg);

        rollG = new JTextField();
        rollG.setBounds(120, 100, 120, 25);
        frameGrade.add(rollG);

        // DSA
        LDSA = new JLabel("DSA: ");
        LDSA.setBounds(50, 150, 130, 25);
        frameGrade.add(LDSA);

        DSA = new JTextField();
        DSA.setBounds(120, 150, 120, 25);
        frameGrade.add(DSA);

        // MMI
        LMMI = new JLabel("MMI: ");
        LMMI.setBounds(50, 200, 130, 25);
        frameGrade.add(LMMI);

        MMI = new JTextField();
        MMI.setBounds(120, 200, 120, 25);
        frameGrade.add(MMI);

        // NT_LAB
        LNT_LAB = new JLabel("NT LAB: ");
        LNT_LAB.setBounds(50, 250, 130, 25);
        frameGrade.add(LNT_LAB);

        NT_LAB = new JTextField();
        NT_LAB.setBounds(120, 250, 120, 25);
        frameGrade.add(NT_LAB);

        // DBMS
        LDBMS = new JLabel("DBMS: ");
        LDBMS.setBounds(50, 300, 130, 25);
        frameGrade.add(LDBMS);

        DBMS = new JTextField();
        DBMS.setBounds(120, 300, 120, 25);
        frameGrade.add(DBMS);

        // Cloud computing
        LCLOUD = new JLabel("Cloud Computing: ");
        LCLOUD.setBounds(50, 350, 200, 25);
        frameGrade.add(LCLOUD);

        CLOUD = new JTextField();
        CLOUD.setBounds(170, 350, 120, 25);
        frameGrade.add(CLOUD);

        // button
        addGrade = new JButton("Add");
        addGrade.setBounds(70, 430, 70, 25);
        addGrade.addActionListener(this);
        frameGrade.add(addGrade);

        clearGrade = new JButton("Clear");
        clearGrade.setBounds(150, 430, 70, 25);
        clearGrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameG.setText("");
                rollG.setText("");
                DSA.setText("");
                MMI.setText("");
                NT_LAB.setText("");
                DBMS.setText("");
                CLOUD.setText("");
            }
        });
        frameGrade.add(clearGrade);

//        frameGrade.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        CalculateGrades cg = new CalculateGrades();
        try (Connection conn = cg.getConnection()) {
            if (conn != null) {
                System.out.println("Connected to the database!");

                try (Statement stmt = conn.createStatement()) {

                    String nameStudent = nameG.getText();
                    int roll = Integer.parseInt(rollG.getText());

                    String q = "SELECT email FROM studentList WHERE name= '" + nameStudent + "' AND roll_no= " + roll;

                    ResultSet rs = stmt.executeQuery(q);

                    if (rs.next()) {
                        double dsa = Double.parseDouble(DSA.getText());
                        double mmi = Double.parseDouble(MMI.getText());
                        double ntLab = Double.parseDouble(NT_LAB.getText());
                        double dbms = Double.parseDouble(DBMS.getText());
                        double cloud = Double.parseDouble(CLOUD.getText());

                        double sum = dsa + mmi + ntLab + dbms + cloud;
                        double avg = sum / 5.0;
                        String grade = "";

                        if (avg >= 90 && avg <= 100) {
                            grade = "O";
                        } else if (avg >= 80 && avg < 90) {
                            grade = "E";
                        } else if (avg >= 70 && avg < 80) {
                            grade = "A";
                        } else if (avg >= 60 && avg < 70) {
                            grade = "B";
                        } else if (avg >= 50 && avg < 60) {
                            grade = "C";
                        } else if (avg >= 40 && avg < 50) {
                            grade = "D";
                        } else {
                            grade = "F";
                        }

                        String query = "INSERT INTO grades (name, Roll_No,Subjects, DSA, MMI, NT_LAB, DBMS, Cloud_Computing, sum, grade_symbol) " +
                                "VALUES ('" + nameStudent + "', " + roll + ", " + 5 + ", " + dsa + ", " + mmi + ", " + ntLab + ", " + dbms + ", " + cloud + ", " +
                                avg + ", '" + grade + "')";

                        stmt.execute(query);
                        System.out.println("Grades inserted successfully!");
                    } else {
                        System.out.println("Name doesn't exist in the student table");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException exe) {
            exe.printStackTrace();
        }
    }

    private void addStudent() {
        CalculateGrades cg = new CalculateGrades();
        try (Connection conn = cg.getConnection()) {
            if (conn != null) {
                System.out.println("Connected to the database!");

                try (Statement stmt = conn.createStatement()) {
                    String nameStudent = name.getText();
                    int roll = Integer.parseInt(Roll.getText());
                    String emailStudent = email.getText();

                    String query = "INSERT INTO studentList (name, Roll_No, email) VALUES ('" + nameStudent + "', " + roll + ", '" + emailStudent + "')";
                    stmt.execute(query);
                    System.out.println("Student added successfully!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GradesFrame();
    }
}
