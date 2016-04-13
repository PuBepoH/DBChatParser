package dataSource;


import countEngine.CountMatches;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class ConsoleScanner {

    private static final Logger log = Logger.getLogger(ConsoleScanner.class);

    public void startConsole(CountMatches countMatches){

        boolean more = true;
        Scanner scanIn = new Scanner(System.in);
        String str;

        while (more){
            log.info("Enter keyword to search:");
            str = scanIn.nextLine();
            ParseResult prsRslt = countMatches.countAllMatches(str);
            prsRslt.resultOut();
            log.info("Do you want search another keyword? Type \"yes\" or \"y\" to search again.");
            str = scanIn.nextLine();
            if (!(str.equals("yes") || str.equals("y"))){
                more = false;
            }
        }

        scanIn.close();

    }

}
