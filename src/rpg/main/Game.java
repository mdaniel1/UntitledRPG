package rpg.main;

import rpg.classes.*;
import rpg.enums.DiceType;
import rpg.exceptions.NoSuchItemException;
import rpg.utils.BattleManager;

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
    public static String ANSI_CLS = "\u001B[2J";
    public static String ANSI_HOME = "\u001B[H";
    public static String ANSI_BOLD = "\u001B[1m";

    public Game(){
        Start();
    }

    public void Start(){
        disableColors();

        System.out.println("Do you wish to enable colours? (Must support ANSI escape sequences, does not work on Windows CMD)\n1) Yes\n2) No");
        int coloursEnabled = Main.getChoice(1,2);
        if(coloursEnabled == 1)
            enableColors();

        CharacterRPG test = new CharacterRPG("Arthur, king of the britons", 23, 23, 6, "He", "");
        CharacterRPG test2 = new CharacterRPG("Lancelot", 50, 50, 4, "He", "");
        test.setWeapon(new Weapon("Excalibur", DiceType.D10, 19, 1));
        test.addSpell(SpellList.HEAL_BASIC);
        /*if(test.hasSpell("W_SPELL_LIGHT_HEAL")){
            test.getSpell("W_SPELL_LIGHT_HEAL").Cast(test);
            test.getSpell("W_SPELL_LIGHT_HEAL").Cast(test, test2);
        }
        test.addItem(ItemList.SMALL_HEALTH_POTION);
        //test.addItem(ItemList.SMALL_HEALTH_POTION);
        try{
            test.useItem(test.getItem(ItemList.SMALL_HEALTH_POTION));
            test.useItem(test.getItem("I_S_HEALTH_POTION"), test2);
        }
        catch(NoSuchItemException e){
            System.out.println(e.getMessage());
        }*/
        test.addItem(ItemList.SMALL_HEALTH_POTION);
        test.addItem(ItemList.SMALL_HEALTH_POTION);
        BattleManager.startBattle(test, test2, true);
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
        ANSI_CLS = "";
        ANSI_HOME = "";
        ANSI_BOLD = "";
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
        ANSI_CLS = "\u001b[2J";
        ANSI_HOME = "\u001b[H";
        ANSI_BOLD = "\u001b[1m";
    }
}
