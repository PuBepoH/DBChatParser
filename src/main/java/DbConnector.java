import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DbConnector {

    static void getConnection() {

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

    }

}