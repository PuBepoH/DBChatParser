import countEngine.ParsingRunner;
import dataSource.ConsoleScanner;
import dataSource.DbConnector;
import org.apache.log4j.Logger;


public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args){


        ConsoleScanner console = new ConsoleScanner();
        DbConnector connect = new DbConnector();
        ParsingRunner prsRun = new ParsingRunner(console,connect.getStmnt());
        prsRun.start();



        log.debug("Program finished.");
    }

}
