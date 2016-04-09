package dataSource;


import countEngine.CountMatches;

import java.util.Scanner;

public class ConsoleScanner {

    private String str = "";

    public void startConsole(CountMatches countMatches){

        boolean more = true;
        Scanner scanIn = new Scanner(System.in);

        while (more){
            System.out.println("Enter keyword to search:");
            str = scanIn.nextLine();
            countMatches.countAllMatches(str);
            System.out.println("Do you want search another keyword? Type \"yes\" or \"y\" to search again.");
            str = scanIn.nextLine();
            if (!(str.equals("yes") || str.equals("y"))){
                more = false;
            }
        }

        scanIn.close();


    }

    public String getStr() {
        return str;
    }
}
