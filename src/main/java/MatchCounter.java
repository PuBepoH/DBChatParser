import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchCounter {

    int getCount(ResultSet rs) {

        int count = 0;
        String regex = ".*:peka:.*",message;
        Pattern r = Pattern.compile(regex);

        try{
            while (rs.next()){
                message = rs.getString("message");
                Matcher m = r.matcher(message);

                while (m.find()){
                    count++;
                }
            }
        }catch (SQLException ex){
            System.out.println("SQLException in MatchCounter: " + ex.getMessage());
        }

        return count;
    }


}
