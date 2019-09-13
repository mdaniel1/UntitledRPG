package rpg.main;

import rpg.classes.CharacterRPG;
import rpg.classes.ItemList;
import rpg.classes.SpellList;
import rpg.exceptions.NoSuchItemException;
import rpg.utils.BattleManager;
import org.fusesource.jansi.AnsiConsole;

import java.lang.reflect.InvocationTargetException;

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
    public static String ANSI_CLS = "\u001b[2J";
    public static String ANSI_HOME = "\u001b[H";

    private static boolean isWindows;
    public static boolean isIntelliJIdea = false;

    public Game(){
        Start();
    }

    public void Start(){
        disableColors();

        String OS = System.getProperty("os.name");
        isWindows = OS.startsWith("Windows");

        if(isWindows && !isIntelliJIdea){
            AnsiConsole.systemInstall();
        }

        System.out.println("Do you wish to enable colours?\n1) Yes\n2) No");
        boolean coloursEnabled = Main.getChoice(1,2) == 1;
        if(coloursEnabled){
            enableColors();
        }

        System.out.println(ANSI_YELLOW + "TEST" + ANSI_RESET);

        CharacterRPG test = new CharacterRPG("Arthur", 30, 50, 1, "He", "");
        CharacterRPG test2 = new CharacterRPG("Perceval", 30, 50, 1, "He", "");
        BattleManager bm = new BattleManager();

        test.addSpell(SpellList.HEAL_BASIC);
        if(test.hasSpell("W_SPELL_LIGHT_HEAL")){
            test.getSpell("W_SPELL_LIGHT_HEAL").Cast(test);
            test.getSpell("W_SPELL_LIGHT_HEAL").Cast(test, test2);
        }
        test.addItem(ItemList.SMALL_HEALTH_POTION);
        //test.addItem(ItemList.SMALL_HEALTH_POTION);
        try{
            test.useItem(test.getItem(ItemList.SMALL_HEALTH_POTION));
            test.useItem(test.getItem(ItemList.SMALL_HEALTH_POTION), test2);
        }
        catch(NoSuchItemException e){
            System.out.println(e.getMessage());
        }

        quit();
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

    public static void quit(){
        if(isWindows){
            AnsiConsole.systemUninstall();
        }
        System.out.println("\n\nThank you for playing.\nPress a key to continue...");
        Main.s.nextLine();
    }
}
