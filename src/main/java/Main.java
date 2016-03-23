import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args){

        Connection connect = new DbConnector().getConnection();

        Statement stmnt = null;
        String query = "select message from mysql.chat_message LIMIT 0, 100";
        int count = 0;

        System.out.println("--------------------------");

        try{
            stmnt = connect.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            String message;
            String regex = ".*:peka:.*";
            Pattern r = Pattern.compile(regex);


            while(rs.next()){
                message = rs.getString("message");
                System.out.println(message);
                Matcher m = r.matcher(message);

                while(m.find()){
                    count++;
                    System.out.println(count + " match!");
                }

            }
        }catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }

    }

}
