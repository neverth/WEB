package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnectionManager {


    static public Connection getConnection(){
         String driverName = "com.mysql.jdbc.Driver";
         String url="jdbc:mysql://47.112.25.38/jsp_shop?";
         String user="jsp_shop";
         String password="f7nAMbCx5N";
        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, user, password);
            return  conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        DBConnectionManager dbConnectionManager = new DBConnectionManager();
        try {
            dbConnectionManager.getConnection().createStatement();
        }catch (SQLException e){

        }
    }

}


