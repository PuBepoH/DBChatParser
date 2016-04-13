package countEngine;


import dataSource.ConsoleScanner;
import dataSource.Expressions;
import org.apache.log4j.Logger;

import java.sql.Statement;
import java.util.ArrayList;


public class ParsingRunner {

    private Expressions[] expressions;
    private CountMatches[] countMatches;
    private static Logger log = Logger.getLogger(ParsingRunner.class);

    public ParsingRunner(ConsoleScanner consoleScanner,Statement stmnt) {

        expressions = consoleScanner.getExpressions();
        ArrayList<CountMatches> countMatchesList = new ArrayList<CountMatches>();

        for(int i = 0; i < expressions.length; i++) {
            countMatchesList.add(new CountMatches(stmnt,expressions[i].getRegex(),"Thread " + i));
        }
        countMatches = countMatchesList.toArray(new CountMatches[countMatchesList.size()]);
    }

    public void start(){

        for (CountMatches cMatch : countMatches) {
            cMatch.start();
            log.info("New thread started");
        }

        for (int i = 0; i < countMatches.length; i++) {
            expressions[i].setMatchCount(countMatches[i].getMatchCount());
            expressions[i].resultOut();
        }

    }


}
