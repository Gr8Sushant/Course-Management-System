import helpers.DBUtils;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class StudentReg extends User {      //Inherits from class User
    static Connection con;

    public static void displayStudents(){  //Displays  currently available Students
        try {
            Connection con = DBUtils.getDbConnection();
            String displayIns = "select * from students";
            Statement st = con.createStatement();
            ResultSet set = st.executeQuery(displayIns);
            while (set.next()){
                int student_id = set.getInt(1);
                String student_name = set.getString(2);
                String address = set.getString(3);
                int course_id = set.getInt(4);
                int level = set.getInt(5);
                System.out.println("-> Id:"+student_id+" | Name:"+student_name+" | Address:"+address+" | Course Id:"+course_id+" | Level:"+level);
                System.out.println("--------------------------------------------------------------------------------");

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static void reg() {   //Registers Student to Database
        StudentReg s = new StudentReg();

        try {
            con = DBUtils.getDbConnection();
            System.out.println("Current list of students.");
            displayStudents();

            System.out.println("\nAdd Student details: \n");
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter Student ID");
            s.setId(sc.nextInt());
            System.out.println("Enter Student name");
            Scanner sc1 = new Scanner(System.in);
            s.setName(sc1.nextLine());
            System.out.println("Enter Address");
            s.setAddress(sc1.nextLine());
            System.out.println("Enter Course id");
            s.setCourseId(sc.nextInt());


            //jdbc query
            String regStd = "insert into students(student_id,student_name,address,course_id,level) values (?,?,?,?,?)";

            PreparedStatement preState = con.prepareStatement(regStd);
            //Setting Parameter values
            preState.setInt(1, s.getId());
            preState.setString(2, s.getName());
            preState.setString(3, s.getAddress());
            preState.setInt(4, s.getCourseId());
            preState.setInt(5, s.getLevel());

            preState.executeUpdate();
            regModule();

            System.out.println("Student registered Successfully!\n");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public static void regModule(){

        try {
            con = DBUtils.getDbConnection();
            System.out.println("\nAdd Student modules: \n");

            System.out.println("Enter Student level");
            Scanner sc = new Scanner(System.in);
            int stdLevel = sc.nextInt();

            System.out.println("Enter Student Id again");
            int stdId = sc.nextInt();

            if (stdLevel == 4){

                ArrayList<Integer> arraymod = new ArrayList<>();
                String returnUsermod = "select module_id from modules where level =4";
                PreparedStatement psmod = con.prepareStatement(returnUsermod);
                ResultSet rsmod = psmod.executeQuery();
                while (rsmod.next()){
                    arraymod.add(rsmod.getInt("module_id")); //Adds all level 4 modules to arraylist
                }

                String q = "insert into student_module(student_id,module1,module2,module3,module4,module5,module6,module7,module8) values (?,?,?,?,?,?,?,?,?);";
                PreparedStatement preState = con.prepareStatement(q);
                //Setting Parameter values
                preState.setInt(1, stdId);
                preState.setInt(2, arraymod.get(0));
                preState.setInt(3, arraymod.get(1));
                preState.setInt(4, arraymod.get(2));
                preState.setInt(5, arraymod.get(3));
                preState.setInt(6, arraymod.get(4));
                preState.setInt(7, arraymod.get(5));
                preState.setInt(8, arraymod.get(6));
                preState.setInt(9, arraymod.get(7));

                preState.executeUpdate();




            }else if (stdLevel == 5){
                ArrayList<Integer> arraymod = new ArrayList<>();
                String returnUsermod = "select module_id from modules where level =5";
                PreparedStatement psmod = con.prepareStatement(returnUsermod);
                ResultSet rsmod = psmod.executeQuery();
                while (rsmod.next()){
                    arraymod.add(rsmod.getInt("module_id")); //Adds all level 5 modules to arraylist
                }

                String q = "insert into student_module(student_id,module1,module2,module3,module4,module5,module6,module7,module8) values (?,?,?,?,?,?,?,?,?);";
                PreparedStatement preState = con.prepareStatement(q);
                //Setting Parameter values
                preState.setInt(1, stdId);
                preState.setInt(2, arraymod.get(0));
                preState.setInt(3, arraymod.get(1));
                preState.setInt(4, arraymod.get(2));
                preState.setInt(5, arraymod.get(3));
                preState.setInt(6, arraymod.get(4));
                preState.setInt(7, arraymod.get(5));
                preState.setInt(8, arraymod.get(6));
                preState.setInt(9, arraymod.get(7));

                preState.executeUpdate();
            }else if (stdLevel == 6){

//
                ArrayList<Integer> arrayDef = new ArrayList<>();
                String returnDef = "SELECT module_id FROM modules WHERE level = 6 and choice = 0";
                PreparedStatement psDef = con.prepareStatement(returnDef);
                ResultSet rsDef = psDef.executeQuery();
                while (rsDef.next()){
                    arrayDef.add(rsDef.getInt("module_id")); //Adds Mandatory level 6 modules to arraylist
                }

                System.out.println("The following are the Mandatory courses you must enroll to:\n");
                System.out.println("--------------------------------------------------------------------------------");
                try {
                    Connection con = DBUtils.getDbConnection();
                    String displayIns = "select module_id,module_name,course_name from modules inner join courses on modules.course_id=courses.course_id where level " +
                            "= 6 and choice =0 ";
                    Statement st = con.createStatement();
                    ResultSet set = st.executeQuery(displayIns);
                    while (set.next()){
                        int module_id = set.getInt(1);
                        String module_name = set.getString(2);
                        String course_name = set.getString(3);
                        System.out.println("-> Id:"+module_id+" | Name:"+module_name+" |  Course Name:"+course_name);
                        System.out.println("--------------------------------------------------------------------------------");

                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }

                System.out.println("\nNow, From the following list of modules, choose 4 modules.");
                System.out.println("Enter the Id of those modules.");

                try {
                    Connection con = DBUtils.getDbConnection();
                    String displayIns = "select module_id,module_name,course_name from modules inner join courses on modules.course_id=courses.course_id where level " +
                            "= 6 and choice =1 ";
                    Statement st = con.createStatement();
                    ResultSet set = st.executeQuery(displayIns);
                    while (set.next()){
                        int module_id = set.getInt(1);
                        String module_name = set.getString(2);
                        String course_name = set.getString(3);
                        System.out.println("-> Id:"+module_id+" | Name:"+module_name+" |  Course Name:"+course_name);
                        System.out.println("--------------------------------------------------------------------------------");

                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                ArrayList<Integer> optIds = new ArrayList<>(); //Adding elected modules to optIds arraylist
                Scanner scanModInt = new Scanner(System.in);
                System.out.println("Enter 1st id:");
                optIds.add(scanModInt.nextInt());
                System.out.println("Enter 2nd id:");
                optIds.add(scanModInt.nextInt());
                System.out.println("Enter 3rd id:");
                optIds.add(scanModInt.nextInt());
                System.out.println("Enter 4th id:");
                optIds.add(scanModInt.nextInt());

                System.out.println("You have selected: "+optIds+"\n");



                String q = "insert into student_module(student_id,module1,module2,module3,module4,module5,module6,module7,module8) values (?,?,?,?,?,?,?,?,?);";
                PreparedStatement preState = con.prepareStatement(q);
                //Setting Parameter values
                preState.setInt(1, stdId);
                preState.setInt(2, arrayDef.get(0));
                preState.setInt(3, arrayDef.get(1));
                preState.setInt(4, arrayDef.get(2));
                preState.setInt(5, arrayDef.get(3));
                preState.setInt(6, optIds.get(0));
                preState.setInt(7, optIds.get(1));
                preState.setInt(8, optIds.get(2));
                preState.setInt(9, optIds.get(3));

                preState.executeUpdate();
            }else {
                System.out.println("Please choose among levels: 4/5/6");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
