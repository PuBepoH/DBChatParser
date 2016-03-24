import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

    public static void main(String[] args){

        Connection connect = new DbConnector().getConnection();

        long done = 0,count = 0,total = 400;
        double progress = 0, step = 0;
        int rows = 100;                        // the number of rows in the query
        Statement stmnt = null;
        ResultSet rs = null;
        String query = "select message from mysql.chat_message LIMIT ";

        //total = DbConnector.getCount(connect);
        System.out.println("Total messages: " + total);

        System.out.println("Progress:");

        MatchCounter m = new MatchCounter();

        step = (double)rows/total*100;

        try {
            stmnt = connect.createStatement();
        }catch (SQLException ex){
            System.out.println("SQLException in connect.createStatement(): " + ex);
        }

        while(progress<100){
            try{
                rs = stmnt.executeQuery(query + String.valueOf(done) + "," + String.valueOf(done+rows));
                while (rs.next()){
                    count+=m.getCount(rs);
                }

            }catch (SQLException ex){
                System.out.println("SQLException in \"while\" cycle: " + ex);
            }
            done += rows;
            progress += step;
            if (progress<100) System.out.println(String.format("%.1f",progress) + "%");
        }

        try{
            stmnt.close();
        }catch (SQLException ex){
            System.out.println("SQLException in closing statement: ");
        }
        System.out.println("100% - Done!");
        System.out.println("Total count = " + count);

    }

}
