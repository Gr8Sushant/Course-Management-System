import helpers.DBUtils;
import java.sql.*;
import java.util.Scanner;

public class Modules {
    static Connection con;

    public void modulesOpt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--------------------------");
        System.out.println("\tModules Panel");
        System.out.println("--------------------------\n");


        System.out.println("Choose one of the five options below\n");
        System.out.println("Press 1: Add Module");
        System.out.println("Press 2: Display Modules");
        System.out.println("Press 3: Edit Module Name");
        System.out.println("Press 4: Go back to previous menu");

        int modselect = sc.nextInt();

        switch (modselect) {
            case 1:
                addModules();
                break;
            case 2:
                displayModule();
                break;
            case 3:
                editModule();
                break;
            case 4:
                break;
        }
    }
    int choice;

    public int choose(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to list it as an optional module? \nPress 1: Yes\nPress 0: No");
        int optChoice = sc.nextInt();
        if (optChoice == 1){
            choice = 1;
        }else {
            choice = 0;
        }
        return choice;
    }
    public void addModules() {
        try {

            con = DBUtils.getDbConnection();
            System.out.println("\nAdd Module details: \n");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Module ID");
            int id = sc.nextInt();
            System.out.println("Enter Module name");
            Scanner sc1 = new Scanner(System.in);
            String name = sc1.nextLine();
            System.out.println("Enter Course Id");
            int courseId = sc.nextInt();
            System.out.println("Enter Course level (4/5/6)");
            int level = sc.nextInt();
            System.out.println("Enter Instructor Id");
            int insId = sc.nextInt();

            if (level == 6){
                choose();
            }

            //jdbc query
            String regStd = "insert into modules(module_id,module_name,course_id,level,instructor_id,choice) values (?,?,?,?,?,?)";

            PreparedStatement preState = con.prepareStatement(regStd);
            //Setting Parameter values
            preState.setInt(1, id);
            preState.setString(2, name);
            preState.setInt(3, courseId);
            preState.setInt(4,level);
            preState.setInt(5, insId);
            preState.setInt(6, choice);

            preState.executeUpdate();

            System.out.println("Module registered Successfully!");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



    public void displayModule(){
        try {
            Connection con = DBUtils.getDbConnection();
            String displayIns = "select * from modules";
            Statement st = con.createStatement();
            ResultSet set = st.executeQuery(displayIns);
            while (set.next()){
                int module_id = set.getInt(1);
                String module_name = set.getString(2);
                int level = set.getInt(4);
                System.out.println("-> "+module_id+": "+module_name+" | Level -> "+level);
                System.out.println("-----------------------------------------------------");

            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void editModule() {
        try {
            con = DBUtils.getDbConnection();
            displayModule();
            System.out.println("\nEnter the Module Id you want to update");
            Scanner sc = new Scanner(System.in);
            int updId = sc.nextInt();

            System.out.println("Enter the new Module name.");
            Scanner sc1 = new Scanner(System.in);
            String newName = sc1.nextLine();

            String query = "update modules set module_name = ? where module_id = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,newName);
            statement.setInt(2,updId);
            int chk = statement.executeUpdate();

            if (chk > 0){
                System.out.println("Module updated successfully");
            }
            else {
                System.out.println("No such Module found");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }


}
