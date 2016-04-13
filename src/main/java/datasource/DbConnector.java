package dataSource;

import org.apache.log4j.Logger;

import java.sql.*;

public class DbConnector {

    private Statement stmnt = null;
    private Connection connect = null;
    private static final String url = "jdbc:mysql://localhost:3306/mysql", user = "root", password = "root";
    private static Logger log = Logger.getLogger(Expressions.class);

    public DbConnector() {

        log.info("Connecting to database...");

        try {

            connect = DriverManager.getConnection(url, user, password);
            log.info("Success!");
            stmnt = connect.createStatement();

        } catch (SQLException ex) {
            log.error("SQLException in Connector" + ex.getMessage());
            System.exit(100);
        }

    }

    public Statement getStmnt() {
        return stmnt;
    }


    @Override
    public void finalize() throws Throwable {
        super.finalize();
        try{
            stmnt.close();
            connect.close();
        }catch (SQLException ex){
            log.error("SQLException in close connection method: " + ex.getMessage());
        }
    }

}