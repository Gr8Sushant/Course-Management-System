import java.util.Scanner;

public class Instructor {
    public void InstructorOpt(){
        Scanner sc = new Scanner(System.in);


        int select = 0;
        do {
            System.out.println("\n------------------------");
            System.out.println("\tINSTRUCTOR PANEL");
            System.out.println("------------------------\n");
            System.out.println("Choose one of the four options below\n");
            System.out.println("Press 1: View current modules");
            System.out.println("Press 2: Assign Marks to students");
            System.out.println("press 3: Go back to previous menu");
            System.out.println("press 4: Exit\n");

            select = sc.nextInt();
            switch (select) {
                case 1:
                    Marks marks = new Marks();
                    ManageInstructor.displayInstructorMods();
                    break;
                case 2:
                    Marks.addMarks();
                    break;
                case 3:
                    Main.main(null);
                    break;
                case 4:
                    break;
            }
        }while (select != 4);
        System.out.println("Thank You for using Course Management System.");

    }
}
