import  java.util.Scanner;

public class Student {

    public void StudentOpt(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n---------------------");
        System.out.println("\tSTUDENT PANEL");
        System.out.println("---------------------\n");

        int select = 0;
        do {

            System.out.println("Choose one of the four options below\n");
            System.out.println("Press 1: Student Registration");
            System.out.println("Press 2: View Student Report");
            System.out.println("Press 3: Go back to previous menu");
            System.out.println("Press 4: Exit\n");
            select = sc.nextInt();
            switch (select) {
                case 1:
                    StudentReg studentReg = new StudentReg();
                    studentReg.reg();
                    break;
                case 2:
                    Marks.report();
                    break;

                case 3:
                    Main main = new Main();
                    main.main(null);
                    break;

                case 4:
                    break;
            }
        }while (select != 4);
        System.out.println("Thank You for using Course Management System.");
    }
}

