package countEngine;


import dataSource.ParseResult;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountMatches {

    private Statement stmnt;
    private int rowsTotal;
    private static final String query = "select message from mysql.chat_message LIMIT ";
    private static final Logger log = Logger.getLogger(CountMatches.class);

    public CountMatches(Statement conStmnt) {
        stmnt = conStmnt;
        makeTotalRows();
    }

    private void makeTotalRows() {

        try {

            log.info("Executing query...");
            ResultSet rs = stmnt.executeQuery("select count(message) from mysql.chat_message");
            System.out.println("Success!");
            if (rs.next()) {
                rowsTotal = rs.getInt("count(message)");
            }
            log.info("Total rows: " + rowsTotal);
            stmnt.close();
        } catch (SQLException e) {
            log.error("SQLException: " + e.getMessage());
        }
        log.debug("Finished makeTotalRows");

    }

    public ParseResult countAllMatches(String regex) {

        log.debug("Started countAllMatches");

        int rowsPerQuery = 10, rowsDone = 0, matchCount = 0;
        ResultSet resultSet;
        Matcher match;
        Pattern pattern = Pattern.compile(regex);

        while (rowsTotal > rowsDone) {
            try {
                resultSet = stmnt.executeQuery(query + String.valueOf(rowsDone) + "," + String.valueOf(rowsPerQuery));
                while (resultSet.next()) {
                    match = pattern.matcher(resultSet.getString("message"));
                    while (match.find()) {
                        matchCount++;
                    }
                }
            } catch (SQLException ex) {
                log.error("SQLException in \"while\" cycle: " + ex.getMessage());
            }
            rowsDone += rowsPerQuery;
            log.info(rowsDone + " rows done.");
        }

        return new ParseResult(regex, matchCount);
    }

}
