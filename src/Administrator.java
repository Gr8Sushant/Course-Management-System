import java.util.Scanner;

public class Administrator {
    public static void administratorOpt(){
        Scanner sc = new Scanner(System.in);


        int select = 0;
        do {
        System.out.println("\n--------------------------");
        System.out.println("\tADMINISTRATOR PANEL");
        System.out.println("--------------------------\n");

        System.out.println("Choose one of the seven options below\n");
        System.out.println("Press 1: Manage/Edit Courses");
        System.out.println("Press 2: Manage/Edit Modules");
        System.out.println("Press 3: Manage/Edit Instructors ");
        System.out.println("Press 4: Generate individual student report");
        System.out.println("Press 5: Select if student will progress to next level");
        System.out.println("Press 6: View all assigned marks");
        System.out.println("Press 7: Go back to previous menu");
        System.out.println("Press 8: Exit\n");

        select = sc.nextInt();
        switch (select) {
            case 1:
                Courses courses = new Courses();
                courses.coursesOpt();
                break;
            case 2:
                Modules modules = new Modules();
                modules.modulesOpt();
                break;
            case 3:
                ManageInstructor manageInstructor = new ManageInstructor();
                manageInstructor.editInstructorOpt();
                break;
            case 4:
                Marks.report();
                break;
            case 5:
                Marks.addRemarks();
                break;
            case 6:
                Marks.allReportData();
                break;
            case 7:
                Main main = new Main();
                main.main(null);
                break;
            case 8:
                break;
        }
        }while (select != 8);
        System.out.println("Thank You for using Course Management System.");

    }

}
