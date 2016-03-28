import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logic {

    private int total = 0, done = 0, match = 0, numberOfLines = 10000;
    private String regex = ".*:peka:.*";
    private String message;
    private final String query = "select message from mysql.chat_message LIMIT ";
    private Pattern pattern = Pattern.compile(regex);
    private Matcher matcher;
    private ResultSet resultSet;


    public void countAllMatches(Statement stmnt) {

        while(total>0){
            try{

                resultSet = stmnt.executeQuery(query + String.valueOf(done) + "," + String.valueOf(numberOfLines));

                while (resultSet.next()){

                    try{
                        do{
                            message = resultSet.getString("message");
                            matcher = pattern.matcher(message);
                            while (matcher.find()){
                                match++;
                            }
                        }while (resultSet.next());
                    }catch (SQLException ex){
                        System.out.println("SQLException in Logic: " + ex.getMessage());
                    }

                }

            }catch (SQLException ex){
                System.out.println("SQLException in \"while\" cycle: " + ex.getMessage());
            }
            done += numberOfLines;
            System.out.println(numberOfLines + " rows done.");
        }

    }

    public int getMatch(){
        return match;
    }

}
