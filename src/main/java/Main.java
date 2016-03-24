import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args){

        Connection connect = new DbConnector().getConnection();

        System.out.println("result is " + DbConnector.getData(connect,"select message from mysql.chat_message LIMIT 0, 100"));

    }

}
