package countEngine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountMatches {

    public CountMatches(Statement conStmnt){
        stmnt = conStmnt;
    }

    private Statement stmnt;
    private int rowsTotal = 0;
    private static final String query = "select message from mysql.chat_message LIMIT ";

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
        System.out.println("Finished makeTotalRows");
    }

    public void countAllMatches(String regex) {

        System.out.println("Started countAllMatches");

        int rowsPerQuery = 100000, rowsDone = 0, matchCount = 0;
        ResultSet resultSet;
        Matcher match;
        Pattern pattern = Pattern.compile(regex);

        while(rowsTotal>rowsDone){
            try{
                resultSet = stmnt.executeQuery(query + String.valueOf(rowsDone) + "," + String.valueOf(rowsPerQuery));
                while (resultSet.next()){
                    try{
                        match = pattern.matcher(resultSet.getString("message"));
                        while (match.find()){
                            matchCount++;
                        }
                    }catch (SQLException ex){
                        System.out.println("SQLException in countEngine.CountMatches: " + ex.getMessage());
                    }
                }
            }catch (SQLException ex){
                System.out.println("SQLException in \"while\" cycle: " + ex.getMessage());
            }
            rowsDone += rowsPerQuery;
            System.out.println(rowsDone + " rows done.");
        }
        System.out.println("100% - Done!");
        System.out.println("Total " + regex + " match count = " + matchCount);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

    }
}
