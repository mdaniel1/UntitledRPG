package rpg.main;

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

        do{
            System.out.print("\n\t Choice : ");
            try{
                choice = Integer.parseInt(Main.s.nextLine());
            }
            catch(Exception e){
                System.out.println("Bad input");
            }
        }while(choice < limitMin || choice > limitMax );
        return choice;
    }

    public static String getFumbleDescriptor(){
            return Game.ANSI_RED + " [FUMBLE] " + Game.ANSI_RESET;
    }

    public static String getCriticalDescriptor(){
            return Game.ANSI_GREEN + " [CRITICAL] " + Game.ANSI_RESET;
    }

    public static void main(String[] args) {
        Game g = new Game();
    }
}
