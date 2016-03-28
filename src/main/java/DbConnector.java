import java.sql.*;

public class DbConnector {

    private Statement stmnt = null;
    private static final String url = "jdbc:mysql://localhost:3306/mysql", user = "root", password = "root";
    private Connection connect = null;

    public void makeConnection() {

        System.out.println("Connecting to database...");

        try {

            connect = DriverManager.getConnection(url, user, password);
            System.out.println("Success!");

        } catch (SQLException ex) {

            System.out.println("Failed to connect.");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }

        try {
            stmnt = connect.createStatement();
        } catch (SQLException ex) {
            System.out.println("SQLException in making statement: " + ex);
        }


    }

    public Statement getStmnt() {
        return stmnt;
    }

}