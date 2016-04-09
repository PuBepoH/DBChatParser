import countEngine.Logic;
import dataSource.DbConnector;

public class Main {

    public static void main(String[] args){

        DbConnector connect = new DbConnector();

        Logic myLogic = new Logic(connect.getStmnt());

        myLogic.makeTotalRows();
        myLogic.countAllMatches();

        System.out.println("100% - Done!");
        System.out.println("Total match count = " + myLogic.getMatch());

        connect.closeConnection();
    }

}
