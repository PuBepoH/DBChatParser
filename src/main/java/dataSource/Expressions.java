package dataSource;


import org.apache.log4j.Logger;

import java.util.regex.Pattern;

public class Expressions {

    private int matchCount = 0;
    private Pattern pattern;
    private static Logger log = Logger.getLogger(Expressions.class);

    public Expressions(String regex){
        this.pattern = Pattern.compile(regex);
    }

    public void resultOut(){
        log.info("Found " + matchCount + "matches for pattern \"" + pattern.toString() + "\"");
    }

    public void addMatchCount(int matchCount) {
        this.matchCount += matchCount;
    }

    public Pattern getPattern() {
        return pattern;
    }

}
