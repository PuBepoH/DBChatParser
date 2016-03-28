import java.sql.*;

public class DbConnector {

    private Statement stmnt = null;
    private static final String url = "jdbc:mysql://localhost:3306/mysql", user = "root", password = "root";
    private Connection conn = null;
    int totalRows = 0;

    public void makeConnection() {

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

        try{
            stmnt = conn.createStatement();
        }catch (SQLException ex){
            System.out.println("SQLException in making statement: " + ex);
        }


    }

    public void makeTotalRows(Connection connect){

        try{

            stmnt = connect.createStatement();
            System.out.println("Executing query...");
            ResultSet rs = stmnt.executeQuery("select count(message) from mysql.chat_message");
            System.out.println("Success!");
            if(rs.next()){
                totalRows = rs.getInt("count(message)");
            }
            System.out.println("Total rows: " + totalRows);

        }catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }

    }

    public Statement getStmnt(){
        return stmnt;
    }

    public int getTotalRows(){
        return totalRows;
    }

}