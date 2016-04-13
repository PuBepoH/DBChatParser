package dataSource;


import org.apache.log4j.Logger;

public class Expressions {

    private int matchCount;
    private String regex;
    private static Logger log = Logger.getLogger(Expressions.class);

    public Expressions(String regex){
        this.regex = regex;
    }

    public void resultOut(){
        log.info("Found " + matchCount + "matches for pattern \"" + regex + "\"");
    }

    public void setMatchCount(int matchCount) {
        this.matchCount = matchCount;
    }

    public String getRegex() {
        return regex;
    }

}
