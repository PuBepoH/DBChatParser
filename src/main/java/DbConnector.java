import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DbConnector {

    Connection getConnection() {

        String url = "jdbc:mysql://localhost:3306/mysql";
        String user = "root";
        String password = "root";

        Connection conn = null;

        System.out.println("Connecting to database...");

        try{

            conn = DriverManager.getConnection(url,user,password);
            System.out.println("Success!");

        }catch(SQLException ex) {

            System.out.println("Failed to connect.");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }
        return conn;
    }

    ResultSet getData(Connection connect, String query){

        Statement stmnt = null;

        try{

            stmnt = connect.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            return rs;

        }catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
        return null;
    }

}