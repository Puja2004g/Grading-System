import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // DatabaseConnection dc = new DatabaseConnection();
        // dc.create();

        GradesFrame gf = new GradesFrame();

        System.out.println("What do you want to do: ");
        System.out.println("1. Add a new Student details");
        System.out.println("2. Add marks to get grades");

        Scanner sc = new Scanner(System.in);
        int ch = sc.nextInt();


        switch (ch) {
            case 1:
                gf.frameStudent.setVisible(true);
                break;
            case 2:
                gf.frameGrade.setVisible(true);
                break;
            default:
                System.out.println("Invalid choice. Please choose 1 or 2.");
                break;
        }
    }
}

