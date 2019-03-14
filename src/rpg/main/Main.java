package rpg.main;

import rpg.classes.CharacterRPG;
import rpg.enums.DiceType;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Random dice = new Random();
    public static Scanner s = new Scanner(System.in);

    public static int rollDice(DiceType d){
        return dice.nextInt(d.getValue()) + 1;
    }

    public static int getChoice(int limitMin, int limitMax) {
        int choice = -1;
        boolean badInput;
        do{
            System.out.print("\n\t Choice : ");
            try{
                choice = Integer.parseInt(Main.s.nextLine());
                badInput = false;
            }
            catch(Exception e){
                System.out.println("Bad input");
                badInput = true;
            }
        }while(choice < limitMin || choice > limitMax || badInput);
        return choice;
    }

    public static String getFumbleDescriptor(){
            return Game.ANSI_RED + " [FUMBLE] " + Game.ANSI_RESET;
    }

    public static String getCriticalDescriptor(){
            return Game.ANSI_GREEN + " [CRITICAL] " + Game.ANSI_RESET;
    }

    public static String getEnemyLevelColoration(CharacterRPG PC, CharacterRPG enemy){
        int levelDifference = enemy.getLevel() - PC.getLevel();
        if(levelDifference < 1) return Game.ANSI_GREEN; //Same level or enemy under-leveled
        if(levelDifference < 3) return Game.ANSI_YELLOW; //Enemy has 1 to 2 level(s) above PC
        return Game.ANSI_RED; //Enemy has more than 2 levels above PC
    }

    public static void clearScreen() {
        System.out.println(Game.ANSI_CLS + Game.ANSI_HOME);
        System.out.flush();
    }

    public static void main(String[] args) {
        Game g = new Game();
    }
}
