import countEngine.CountMatches;
import dataSource.ConsoleScanner;
import dataSource.DbConnector;


public class Main {

    public static void main(String[] args){

        ConsoleScanner console = new ConsoleScanner();
        DbConnector connect = new DbConnector();
        CountMatches myCountMatches = new CountMatches(connect.getStmnt());

        myCountMatches.makeTotalRows();
        console.startConsole(myCountMatches);
        System.out.println("Program finished.");
    }

}
