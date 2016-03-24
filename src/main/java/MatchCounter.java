import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MatchCounter {

    private int count = 0;
    private String regex = ".*:peka:.*";
    private String message;
    private Pattern r = Pattern.compile(regex);
    private Matcher m;

    int getCount(ResultSet rs) {

        count = 0;

        try{
            while (rs.next()){
                message = rs.getString("message");
                m = r.matcher(message);
            //    System.out.println(message);

                while (m.find()){
                    count++;
                    System.out.println(message + " - match! " + count);
                }
            }
        }catch (SQLException ex){
            System.out.println("SQLException in MatchCounter: " + ex.getMessage());
        }

        return count;
    }


}
