

public class Main {

    public static void main(String[] args){

        DbConnector connect = new DbConnector();

        connect.makeConnection();

        Logic myLogic = new Logic();

        myLogic.getTotalRows(connect.getStmnt());
        myLogic.countAllMatches(connect.getStmnt());

        System.out.println("100% - Done!");
        System.out.println("Total match count = " + myLogic.getMatch());

    }

}
