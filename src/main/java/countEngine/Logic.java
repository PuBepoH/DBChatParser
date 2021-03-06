package countEngine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logic {

    public Logic(Statement conStmnt){
        stmnt = conStmnt;
    }

    private Statement stmnt;
    private int rowsTotal = 0, rowsDone = 0, matchCount = 0;
    private String regex = ".*:peka:.*";
    private static final String query = "select message from mysql.chat_message LIMIT ";
    private Pattern pattern = Pattern.compile(regex);

    public void makeTotalRows(){

        try{

            System.out.println("Executing query...");
            ResultSet rs = stmnt.executeQuery("select count(message) from mysql.chat_message");
            System.out.println("Success!");
            if(rs.next()){
                rowsTotal = rs.getInt("count(message)");
            }
            System.out.println("Total rows: " + rowsTotal);

        }catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }

    }

    public void countAllMatches() {

        int rowsPerQuery = 100000;
        String message;
        Matcher matcher;
        ResultSet resultSet;

        while(rowsTotal>0){
            try{

                resultSet = stmnt.executeQuery(query + String.valueOf(rowsDone) + "," + String.valueOf(rowsPerQuery));

                while (resultSet.next()){

                    try{
                        do{
                            message = resultSet.getString("message");
                            matcher = pattern.matcher(message);
                            while (matcher.find()){
                                matchCount++;
                            }
                        }while (resultSet.next());
                    }catch (SQLException ex){
                        System.out.println("SQLException in countEngine.Logic: " + ex.getMessage());
                    }

                }

            }catch (SQLException ex){
                System.out.println("SQLException in \"while\" cycle: " + ex.getMessage());
            }
            rowsDone += rowsPerQuery;
            System.out.println(rowsDone + " rows done.");
        }

    }

    public int getMatch(){
        return matchCount;
    }

}
