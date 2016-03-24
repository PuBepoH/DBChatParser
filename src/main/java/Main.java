import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

    public static void main(String[] args){

        Connection connect = new DbConnector().getConnection();

        long total = 8700;
        long done = 0,count =0;
        double progress = 0;
        Statement stmnt = null;
        ResultSet rs = null;
        String query = "select message from mysql.chat_message LIMIT ";

        //total = DbConnector.getCount(connect);
        System.out.println("Total messages: " + total);

        System.out.println("Progress:");

        while(progress<100){
            try{
                stmnt = connect.createStatement();
                rs = stmnt.executeQuery(query + String.valueOf(done) + "," + String.valueOf(done+1000));
                while (rs.next()){

                }
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
