package countEngine;


import dataSource.ConsoleScanner;
import dataSource.Expressions;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ParsingRunner {

    private int totalRows;
    private Statement stmnt;
    private Expressions[] expressions;
    private CountMatches[] countMatches;
    private static Logger log = Logger.getLogger(ParsingRunner.class);

    public ParsingRunner(ConsoleScanner consoleScanner,Statement stmnt) {

        expressions = consoleScanner.getExpressions();

        this.stmnt = stmnt;

        try {

            log.info("Executing query...");
            ResultSet rs = stmnt.executeQuery("select count(message) from mysql.chat_message");
            System.out.println("Success!");
            if (rs.next()) {
                totalRows = rs.getInt("count(message)");
            }
            log.info("Total rows: " + totalRows);
        } catch (SQLException e) {
            log.error("SQLException: " + e.getMessage());
        }
        log.debug("Finished makeTotalRows");

        ArrayList<CountMatches> countMatchesList = new ArrayList<CountMatches>();

        for(int i = 0; i < expressions.length; i++) {
            countMatchesList.add(new CountMatches(stmnt,expressions[i].getRegex(),"Thread " + i,totalRows));
        }

        countMatches = countMatchesList.toArray(new CountMatches[countMatchesList.size()]);

    }

    public void start(){

        for (CountMatches cMatch : countMatches) {
            cMatch.start();
            log.debug("Starting new thread");
        }

        for (int i = 0; i < countMatches.length; i++) {
            expressions[i].setMatchCount(countMatches[i].getMatchCount());
            expressions[i].resultOut();
        }

    }


}
