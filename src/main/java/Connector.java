import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    public static void main(String[] args){
        Connection conn = null;

        try{

            conn = DriverManager.getConnection("jdbc::mysql://localhost/test?" + "user=root&password=root");
            System.out.println("Success!");

        }catch(SQLException ex) {

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }
    }

}
