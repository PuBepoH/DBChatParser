import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args){

        Connection connect = new DbConnector().getConnection();

        long total = 8700700;
        long done = 0,count =0;
        double progress = 0;
        Statement stmnt = null;
        ResultSet rs = null;

        //total = DbConnector.getCount(connect);
        System.out.println("Total messages: " + total);

        System.out.println("Progress:");

        while(progress<100){
            try{
                stmnt = connect.createStatement();
                rs = stmnt.executeQuery("select message from mysql.chat_message LIMIT 0, 1000");
            }catch (SQLException ex){
                System.out.println("SQLException in \"while\" cycle: " + ex);
            }
            done += 1000;
            progress = (double)done/total*100;
            if (progress<100) System.out.println(String.format("%.1f",progress) + "%");
        }
        System.out.println("100% - Done!");
        System.out.println("Total count = " + count);
    }

}
