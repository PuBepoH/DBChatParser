import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args){

        Connection connect = new DbConnector().getConnection();

        Statement stmnt = null;
        String query = "select message,uid from mysql.chat_message LIMIT 0, 10";

        try{
            stmnt = connect.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            while(rs.next()){
                String message = rs.getString("message");
                System.out.println(message);
            }
        }catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }

    }

}
