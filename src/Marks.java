import helpers.DBUtils;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Marks {

    public static void addMarks(){
        try {
            Connection con = DBUtils.getDbConnection();
            Scanner scInt = new Scanner(System.in);
            ManageInstructor.displayInstructors();
            System.out.println("Enter your Instructor ID");
            int insId = scInt.nextInt();

            StudentReg.displayStudents();
            System.out.println("\nEnter the Student id you want to add marks to");
            int stdId = scInt.nextInt();

            ArrayList<Integer> moduleArray = new ArrayList<>();
            String returnUsermod = "SELECT module_id FROM modules WHERE instructor_id ="+insId;
            PreparedStatement psmod = con.prepareStatement(returnUsermod);
            ResultSet rsmod = psmod.executeQuery();
            while (rsmod.next()){
                moduleArray.add(rsmod.getInt("module_id"));
            }
            System.out.println(moduleArray);

            for (int i = 0; i < moduleArray.size();i++ ){
                System.out.println("Enter marks for module: "+moduleArray.get(i));
                int modMark = scInt.nextInt();

                int modId = moduleArray.get(i);

                BufferedWriter bw = new BufferedWriter(new FileWriter("marks.txt",true));
                bw.write(stdId+","+modId+","+modMark);
                bw.newLine();
                bw.close();

                String regStd = "insert into marks(student_id,module_id,marks) values (?,?,?)";

                PreparedStatement preState = con.prepareStatement(regStd);
                //Setting Parameter values
                preState.setInt(1, stdId);
                preState.setInt(2, moduleArray.get(i));
                preState.setInt(3, modMark);

                preState.executeUpdate();
            }
            System.out.println("Data Added Successfully");



        }catch (SQLException | IOException e){
            System.out.println("Please Select the correct option");
            e.printStackTrace();
        }

    }
    public static void report(){
        try {
            Connection con = DBUtils.getDbConnection();
            Scanner sc = new Scanner(System.in);
            StudentReg.displayStudents();
            System.out.println("\nEnter the Student's id whose marks you want to view.");
            int stdId = sc.nextInt();

            String retIns = "select * from marks where student_id ="+stdId;

            Statement st = con.createStatement();
            ResultSet set = st.executeQuery(retIns);
            System.out.println("\n-----------------------------");
            System.out.println("\tReport Slip");
            System.out.println("-----------------------------\n");
            while (set.next()){
                int student_id = set.getInt(1);
                int module_id = set.getInt(2);
                int marks = set.getInt(3);
                String remarks = set.getString(4);
                System.out.println("-> Student ID: "+student_id+": Module_Id: "+module_id+" | Marks : -> "+marks+" | Remarks: "+remarks);
                System.out.println("-------------------------------------------------------------------------");

            }



        }catch (SQLException e){
            System.out.println("Please Select the correct option");
            e.printStackTrace();
        }
    }
    public static void allReportData(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("marks.txt"));
            String s;
            System.out.println("Student ID\tModule ID\tMarks Assigned");

            while ((s=br.readLine()) !=null){

                String[] data;
                data = s.split(",");
                for (int i = 0; i < 3; i++)
                {
                    System.out.print(data[i]+" \t\t\t");
                }
                System.out.println();
            }

        }catch (Exception e){
            System.out.println("Null detected in file");
        }

    }

    public static void addRemarks(){
        Connection con;

        try {
            con = DBUtils.getDbConnection();

            String displayIns ="SELECT student_id,Module_name,Marks,Remarks FROM marks INNER JOIN modules ON marks.module_id = modules.module_id;";
            Statement st = con.createStatement();
            ResultSet set = st.executeQuery(displayIns);
            while (set.next()){
                int student_id = set.getInt(1);
                String module_name = set.getString(2);
                int marks = set.getInt(3);
                String remarks = set.getString(4);

                System.out.println("-> Student ID:"+student_id+": Module Name:"+module_name+" | Marks -> "+marks+" | Remarks:"+remarks);
                System.out.println("-----------------------------------------------------------------------------------");

            }


            System.out.println("\nEnter the student Id you want to update");
            Scanner sc = new Scanner(System.in);
            int updId = sc.nextInt();

            System.out.println("\nEnter Pass if the student can progress to next level or Fail if failed.");
            Scanner sc1 = new Scanner(System.in);
            String newName = sc1.nextLine();

            String query = "update marks set remarks = ? where student_id = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,newName);
            statement.setInt(2,updId);
            int chk = statement.executeUpdate();

            if (chk > 0){
                System.out.println("Remarks added Successfully");
            }
            else {
                System.out.println("No such Student Id found.");
            }

        }catch (SQLException e){
            System.out.println("Please Select the correct option");
            e.printStackTrace();

        }
    }
}
