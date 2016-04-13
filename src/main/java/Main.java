import countEngine.CountMatches;
import dataSource.ConsoleScanner;
import dataSource.DbConnector;
import org.apache.log4j.Logger;


public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args){

        ConsoleScanner console = new ConsoleScanner();
        DbConnector connect = new DbConnector();
        CountMatches myCountMatches = new CountMatches(connect.getStmnt());

        console.startConsole(myCountMatches);
        log.debug("Program finished.");
    }

}
