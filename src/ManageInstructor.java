import helpers.DBUtils;
import java.sql.*;
import java.util.Scanner;

public class ManageInstructor extends User {
    Connection con;

    public void editInstructorOpt(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n-------------------------------");
        System.out.println("\tEdit Instructor Details");
        System.out.println("-------------------------------\n");
        System.out.println("Choose one of the two options below\n");
        System.out.println("Press 1: Add Instructor");
        System.out.println("Press 2: Display Instructors");
        System.out.println("Press 3: Edit Instructor Name");
        System.out.println("Press 4: Change Assigned module");
        System.out.println("Press 5: Go back to previous menu\n");


        while (!sc.hasNextInt()) {
            sc.next();
        }
        int select = sc.nextInt();
        switch (select)

        {
            case 1:
                addInstructor();
                break;
            case 2:
                displayInstructors();
                break;
            case 3:
                updateInstructorName();
                break;
            case 4:
                updateInstructorModule();
                break;
            case 5:
                Main.main(null);
                break;

            default:
                System.out.println("choose from above options");
        }

    }

    public void addInstructor (){
        ManageInstructor s = new ManageInstructor();

        try {
            con = DBUtils.getDbConnection();
            System.out.println("\nAdd Instructor details: \n");
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter Instructor ID");
            s.setId(sc.nextInt());
            System.out.println("Enter Instructor name");
            Scanner sc1 = new Scanner(System.in);
            s.setName(sc1.nextLine());

            //jdbc query
            String regStd = "insert into instructors(instructor_id,instructor_name) values (?,?)";

            PreparedStatement preState = con.prepareStatement(regStd);
            //Setting Parameter values
            preState.setInt(1, s.getId());
            preState.setString(2, s.getName());

            preState.executeUpdate();

            System.out.println("Instructor registered Successfully!\n");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void displayInstructors(){
        System.out.println("Instructor details: \n");


        try {
            Connection con = DBUtils.getDbConnection();
            String displayIns = "select * from instructors";
            Statement st = con.createStatement();
            ResultSet set = st.executeQuery(displayIns);
            while (set.next()){
                int instructor_id = set.getInt(1);
                String instructor_name = set.getString(2);
                System.out.println("-> "+instructor_id+": "+instructor_name);
                System.out.println("--------------------------------------------");

            }

       }catch (SQLException e){
           e.printStackTrace();
       }
    }

    public void updateInstructorModule(){
        try {
            con = DBUtils.getDbConnection();
            Modules modules = new Modules();
            modules.displayModule();
            System.out.println("\nEnter the module Id you want to assign instructor");
            Scanner sc = new Scanner(System.in);
            int updId = sc.nextInt();

            System.out.println("Enter the new Instructor id from the following list.");
            displayInstructors();
            Scanner sc1 = new Scanner(System.in);
            int newId = sc1.nextInt();

            String query = "update modules set instructor_id = ? where module_id = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1,newId);
            statement.setInt(2,updId);
            int chk = statement.executeUpdate();

            if (chk > 0){
                System.out.println("Instructor updated successfully");
            }
            else {
                System.out.println("No such data found");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    public void updateInstructorName(){
        try {
            con = DBUtils.getDbConnection();
            displayInstructors();
            System.out.println("\nEnter the Instructor Id you want to update");
            Scanner sc = new Scanner(System.in);
            int updId = sc.nextInt();

            System.out.println("Enter the new Instructor name.");
            Scanner sc1 = new Scanner(System.in);
            String newName = sc1.nextLine();

            String query = "update instructors set instructor_name = ? where instructor_id = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,newName);
            statement.setInt(2,updId);
            int chk = statement.executeUpdate();

            if (chk > 0){
                System.out.println("Instructor's Name updated successfully");
            }
            else {
                System.out.println("No such Instructor Id found.");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void displayInstructorMods(){
        try {
            Connection con = DBUtils.getDbConnection();
            Scanner sc = new Scanner(System.in);
            ManageInstructor edit = new ManageInstructor();
            edit.displayInstructors();
            System.out.println("\nEnter the Instructor's id whose current modules you want to view.");
            int insId = sc.nextInt();

            String retIns = "select * from modules where instructor_id ="+insId;

            Statement st = con.createStatement();
            ResultSet set = st.executeQuery(retIns);
            while (set.next()){
                int module_id = set.getInt(1);
                String module_name = set.getString(2);
                int level = set.getInt(4);
                int courseId = set.getInt(3);
                int instructorID = set.getInt(5);
                System.out.println("-> "+module_id+": "+module_name+" | Level -> "+level+" | course Id -> "+courseId+" | Instructor Id -> "+instructorID);
                System.out.println("------------------------------------------------------------------------------------");

            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }


}
