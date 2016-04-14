package countEngine;


import dataSource.ConsoleScanner;
import dataSource.Expressions;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ParsingRunner {

    private int totalRows, doneRows;
    private Statement stmnt;
    private Expressions[] expressions;
    private CountMatches[] countMatches;

    private static Logger log = Logger.getLogger(ParsingRunner.class);
    private static final String query = "select message from mysql.chat_message LIMIT ";

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



    }

    public void start(){

        ResultSet resultSet;

        int rowsPerQuery = 5000000;

        ArrayList<CountMatches> countMatchesList = new ArrayList<CountMatches>();

        for(int i = 0; i < expressions.length; i++) {
            countMatchesList.add(new CountMatches(stmnt,expressions[i].getPattern().toString(),"Thread " + i,totalRows));
        }
        countMatches = countMatchesList.toArray(new CountMatches[countMatchesList.size()]);
    /////////////////////////////////
        while (totalRows > doneRows) {
            try {
                resultSet = stmnt.executeQuery(query + String.valueOf(doneRows) + "," + String.valueOf(rowsPerQuery));

                for (int threadNumber = 0; threadNumber < countMatches.length; threadNumber++) {
                    countMatches[threadNumber].start(resultSet,expressions[threadNumber].getPattern(),threadNumber,expressions);
                    log.debug("Starting new thread");
                }
            } catch (SQLException ex) {
                log.error("SQLException in \"while\" cycle: " + ex.getMessage());
            }
            doneRows += rowsPerQuery;

        }
    ////////////////////////////////


        for (int i = 0; i < countMatches.length; i++) {
            expressions[i].addMatchCount(countMatches[i].getMatchCount());
            expressions[i].resultOut();
        }

    }


}
