package dataSource;


import java.util.Scanner;

public class ConsoleScanner {

    private String str = "";

    public void readConsole(){

        Scanner scanIn = new Scanner(System.in);
        str = scanIn.nextLine();

        scanIn.close();
        System.out.println("your pattern: " + str);

    }

    public String getStr() {
        return str;
    }
}
