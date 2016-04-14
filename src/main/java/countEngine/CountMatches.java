package countEngine;


import dataSource.Expressions;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountMatches implements Runnable {

    private int totalRows, matchCount, threadNumber;
    private Thread thread;
    private Pattern pattern;
    private Statement stmnt;
    private ResultSet resultSet;
    private Expressions[] expressions;
    private String threadName;
    private static final Logger log = Logger.getLogger(CountMatches.class);

    public CountMatches(Statement stmnt,String regex,String threadName,int totalRows) {

        this.totalRows = totalRows;
        this.stmnt = stmnt;
        this.threadName = threadName;
    }

    public void run() {

        Matcher match;
        matchCount = 0;

        log.debug("Thread " + threadName + " started, Total rows: " + totalRows);
            resultSet.close();
            if (stmnt != null) {
                log.info("STMNT OK in thread " + threadNumber);
            }
            if (resultSet != null) {
                log.info("resultSet OK in thread " + threadNumber);
            }

        try {
            while (resultSet.next()) {
                match = pattern.matcher(resultSet.getString("message"));
                while (match.find()) {
                    matchCount++;
                }
            }
        } catch (SQLException ex) {
            log.error("SQLExceptoin in CountMatch cycle" + ex.getMessage());
        }
        expressions[threadNumber].addMatchCount(matchCount);

        log.info("Added " + matchCount + " matches for " + pattern.toString() + "in thread " + threadNumber);

    }

    public void start(ResultSet resultSet, Pattern pattern, int threadNumber, Expressions[] expressions) {
        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
            this.resultSet = resultSet;
            this.pattern = pattern;
            this.threadNumber = threadNumber;
            this.expressions = expressions;
        }
    }

    public int getMatchCount() {
        return matchCount;
    }
}
