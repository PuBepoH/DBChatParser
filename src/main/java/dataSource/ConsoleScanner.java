package dataSource;


import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleScanner {
    
    Scanner scanIn = new Scanner(System.in);

    private static final Logger log = Logger.getLogger(ConsoleScanner.class);

    public Expressions[] getExpressions(){

        boolean more = true;
        
        String str;
        ArrayList<Expressions> expressionsList = new ArrayList<Expressions>();

        while (more){
            log.info("Enter keyword to search:");
            str = scanIn.nextLine();
            expressionsList.add(new Expressions(str));
            log.info("Do you want search another keyword? Type \"yes\" or \"y\" to search again.");
            str = scanIn.nextLine();
            if (!(str.equals("yes") || str.equals("y"))){
                more = false;
            }
        }

        return expressionsList.toArray(new Expressions[expressionsList.size()]);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        scanIn.close();
    }
}
