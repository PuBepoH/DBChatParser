import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logic {

    private int totalRows = 0, done = 0, match = 0, rowsPerQuery = 10000;
    private String regex = ".*:peka:.*";
    private String message;
    private final String query = "select message from mysql.chat_message LIMIT ";
    private Pattern pattern = Pattern.compile(regex);
    private Matcher matcher;
    private ResultSet resultSet;

    public void getTotalRows(Statement stmnt){

        try{

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

    public void countAllMatches(Statement stmnt) {

        while(totalRows>0){
            try{

                resultSet = stmnt.executeQuery(query + String.valueOf(done) + "," + String.valueOf(rowsPerQuery));

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
            done =+ rowsPerQuery;
            System.out.println(done + " rows done.");
        }

    }

    public int getMatch(){
        return match;
    }

}
