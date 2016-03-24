import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args){

        Connection connect = new DbConnector().getConnection();

        int messages = 0;

        messages = DbConnector.getCount(connect);
        System.out.println("Total messages: " + messages);


    }

}
