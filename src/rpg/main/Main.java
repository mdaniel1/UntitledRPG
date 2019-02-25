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

    public static String getFumbleDescriptor(boolean isAlly){
        if(isAlly)
            return Game.ANSI_RED + " [FUMBLE] " + Game.ANSI_RESET;
        else
            return Game.ANSI_GREEN + " [FUMBLE] " + Game.ANSI_RESET;
    }

    public static String getCriticalDescriptor(boolean isAlly){
        if(isAlly)
            return Game.ANSI_GREEN + " [FUMBLE] " + Game.ANSI_RESET;
        else
            return Game.ANSI_RED + " [FUMBLE] " + Game.ANSI_RESET;
    }

    public static void main(String[] args) {
	// write your code here
    }
}
