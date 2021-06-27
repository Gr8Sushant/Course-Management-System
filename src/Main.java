import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("\n=================================");
        System.out.println("\tCourse Management System");
        System.out.println("=================================\n");
        System.out.println("You want to login as:\n");
        System.out.println("Press 1: Administrator");
        System.out.println("Press 2: Student");
        System.out.println("Press 3: Instructor");
        System.out.println("Press 4: Exit\n");
        while (!sc.hasNextInt()) {
            sc.next();
        }
        int select = sc.nextInt();
        switch (select) {
            case 1:
                Administrator.administratorOpt(); //calls for admin options
                break;
            case 2:
                Student student = new Student();    //calls for Student options
                student.StudentOpt();
                break;
            case 3:
                Instructor instructor = new Instructor();   //calls for Instructor options
                instructor.InstructorOpt();
                break;

            case 4:
                break;

        }
    }
}
