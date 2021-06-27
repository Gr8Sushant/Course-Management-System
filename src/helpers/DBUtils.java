package helpers;

import java.sql.*;
public class DBUtils {
    public static  Connection getDbConnection() throws SQLException {

        String connectionString = "jdbc:mysql://"+ Config.dbHost + ":" + Config.dbPort + "/" + Config.dbName + "?useTimezone=true&serverTimezone=UTC" ;

        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

        return DriverManager.getConnection(connectionString, Config.dbUser, Config.dbPass);
    }
}
