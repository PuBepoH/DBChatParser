import countEngine.CountMatches;
import dataSource.ConsoleScanner;
import dataSource.DbConnector;


public class Main {

    public static void main(String[] args){

        ConsoleScanner console = new ConsoleScanner();
        console.readConsole();
        DbConnector connect = new DbConnector();
        CountMatches myCountMatches = new CountMatches(connect.getStmnt());
        myCountMatches.makeTotalRows();
        myCountMatches.countAllMatches(":peka:");
        myCountMatches.countAllMatches(":grumpy:");

    }

}
