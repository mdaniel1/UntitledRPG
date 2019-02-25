package rpg.main;

public class Game {
    public static String ANSI_RESET = "\u001B[0m";
    public static String ANSI_BLACK = "\u001B[30m";
    public static String ANSI_RED = "\u001B[31m";
    public static String ANSI_GREEN = "\u001B[32m";
    public static String ANSI_YELLOW = "\u001B[33m";
    public static String ANSI_BLUE = "\u001B[34m";
    public static String ANSI_PURPLE = "\u001B[35m";
    public static String ANSI_CYAN = "\u001B[36m";
    public static String ANSI_WHITE = "\u001B[37m";

    public Game(){
        disableColors();

        System.out.println("Do you wish to enable colours?\n1) Yes\n2) No");
        int coloursEnabled = Main.getChoice(1,2);
        if(coloursEnabled == 1)
            enableColors();
    }


    private void disableColors(){
        ANSI_RESET = "";
        ANSI_BLACK = "";
        ANSI_RED = "";
        ANSI_GREEN = "";
        ANSI_YELLOW = "";
        ANSI_BLUE = "";
        ANSI_PURPLE = "";
        ANSI_CYAN = "";
        ANSI_WHITE = "";
    }

    private void enableColors(){
        ANSI_RESET = "\u001B[0m";
        ANSI_BLACK = "\u001B[30m";
        ANSI_RED = "\u001B[31m";
        ANSI_GREEN = "\u001B[32m";
        ANSI_YELLOW = "\u001B[33m";
        ANSI_BLUE = "\u001B[34m";
        ANSI_PURPLE = "\u001B[35m";
        ANSI_CYAN = "\u001B[36m";
        ANSI_WHITE = "\u001B[37m";
    }
}
