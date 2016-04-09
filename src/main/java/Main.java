import countEngine.CountMatches;
import dataSource.DbConnector;

public class Main {

    public static void main(String[] args){

        DbConnector connect = new DbConnector();

        CountMatches myCountMatches = new CountMatches(connect.getStmnt());

        myCountMatches.makeTotalRows();
        myCountMatches.countAllMatches();

        System.out.println("100% - Done!");
        System.out.println("Total match count = " + myCountMatches.getMatch());

        connect.closeConnection();
    }

}
