import java.sql.*;

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

    static int getCount(Connection connect){

        Statement stmnt = null;
        int count = 0;

        try{

            stmnt = connect.createStatement();
            System.out.println("Executing query...");
            ResultSet rs = stmnt.executeQuery("select count(message) from mysql.chat_message");
            System.out.println("Success!");
            if(rs.next()){
                count = rs.getInt("count(message)");
            }

        }catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
        return count;
    }

}