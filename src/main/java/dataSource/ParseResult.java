package dataSource;


import org.apache.log4j.Logger;

public class ParseResult {

    private static Logger log = Logger.getLogger(ParseResult.class);

    private int matchCount;
    private String regex;

    public ParseResult(String regex, int matchCount){
        this.matchCount = matchCount;
        this.regex = regex;
    }

    public void resultOut(){
        log.info("Found " + matchCount + "matches for pattern \"" + regex + "\"");
    }

}
