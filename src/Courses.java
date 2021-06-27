import helpers.DBUtils;

import java.sql.*;
import java.util.Scanner;


public class Courses {
    static Connection con;

    public void coursesOpt(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--------------------------");
        System.out.println("\tCourses Panel");
        System.out.println("--------------------------\n");
        System.out.println("Choose one of the seven options below\n");
        System.out.println("Press 1: Add Course");
        System.out.println("Press 2: Display available courses");
        System.out.println("Press 3: Edit Course Name");
        System.out.println("Press 4: Cancel Course");
        System.out.println("Press 5: Delete Course");
        System.out.println("Press 6: Restore Cancelled Course");
        System.out.println("Press 7: Go back to previous menu\n");


        while (!sc.hasNextInt()) {
            sc.next();
        }
        int select = sc.nextInt();
        switch (select)

        {
            case 1:
                courseReg();
                break;
            case 2:
                displayCourses();
            case 3:
                editCourseName();
                break;
            case 4:
                cancelCourse();
                break;
            case 5:
                deleteCourse();
                break;
            case 6:
                restoreCourse();
                break;

            case 7:
                break;
        }
    }


    public void courseReg() {
        try {
            con = DBUtils.getDbConnection();
            System.out.println("\nAdd Course details: \n");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Course ID");
            int crsId = sc.nextInt();
            System.out.println("Enter Course name");
            Scanner sc1 = new Scanner(System.in);
            String crsName = (sc1.nextLine());


            //jdbc query
            String regStd = "insert into courses(course_id,course_name) values (?,?)";

            PreparedStatement preState = con.prepareStatement(regStd);
            //Setting Parameter values
            preState.setInt(1, crsId);
            preState.setString(2, crsName);


            preState.executeUpdate();

            System.out.println("Student registered Successfully!");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
    public void displayCourses(){
        try {
            Connection con = DBUtils.getDbConnection();
            String displayIns = "select * from courses";
            Statement st = con.createStatement();
            ResultSet set = st.executeQuery(displayIns);
            while (set.next()){
                int course_id = set.getInt(1);
                String course_name = set.getString(2);
                System.out.println("-> "+course_id+": "+course_name);
                System.out.println("--------------------------------------------");

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void editCourseName (){
        try {
            con = DBUtils.getDbConnection();
            displayCourses();
            System.out.println("Enter the Course Id you want to update");
            Scanner sc = new Scanner(System.in);
            int updId = sc.nextInt();

            System.out.println("Enter the new Course name.");
            Scanner sc1 = new Scanner(System.in);
            String newName = sc1.nextLine();

            String query = "update courses set course_name = ? where course_id = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,newName);
            statement.setInt(2,updId);
            int chk = statement.executeUpdate();

            if (chk > 0){
                System.out.println("Course updated successfully");
                displayCourses();
            }
            else {
                System.out.println("No such Course found");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    private void cancelCourse() {
        try {
            con = DBUtils.getDbConnection();
            displayCourses();
            System.out.println("\nCancel Course: \n");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Course ID you want to cancel");
            int crsId = sc.nextInt();


            //jdbc query
            String cancelStr = "insert into courses_cancelled (course_cncl_id,course_cncl_name) " +
                    "select course_id, course_name from courses where course_id = ?;";
            String dltStr = "DELETE FROM courses WHERE course_id = ?;";

            PreparedStatement preState = con.prepareStatement(cancelStr);
            PreparedStatement nxtState = con.prepareStatement(dltStr);
            //Setting Parameter values
            preState.setInt(1, crsId);
            nxtState.setInt(1,crsId);

            preState.executeUpdate();
            nxtState.executeUpdate();

            System.out.println("Course Cancelled Successfully!\n");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void restoreCourse(){
        try {
            con = DBUtils.getDbConnection();
            System.out.println("\nRestore Cancelled Course: \n");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Course ID you want to restore");
            int crsId = sc.nextInt();


            //jdbc query
            String cancelStr = "insert into courses (course_id,course_name) " +
                    "select course_cncl_id, course_cncl_name from courses_cancelled where course_cncl_id = ?;";
            String dltStr = "DELETE FROM courses_cancelled WHERE course_cncl_id = ?;";

            PreparedStatement preState = con.prepareStatement(cancelStr);
            PreparedStatement nxtState = con.prepareStatement(dltStr);
            //Setting Parameter values
            preState.setInt(1, crsId);
            nxtState.setInt(1,crsId);

            preState.executeUpdate();
            nxtState.executeUpdate();

            System.out.println("Course Restored Successfully!\n");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void deleteCourse(){
        try {
            con = DBUtils.getDbConnection();
            displayCourses();
            System.out.println("\nDelete Course: \n");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Course ID");
            int crsId = sc.nextInt();


            //jdbc query
            String regStd = "delete from courses where course_id=?";

            PreparedStatement preState = con.prepareStatement(regStd);
            //Setting Parameter values
            preState.setInt(1, crsId);

            int chk = preState.executeUpdate();

            if (chk > 0){
                System.out.println("Course deleted successfully");
                displayCourses();
            }
            else {
                System.out.println("No such data found");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}

