package rpg.utils;

import rpg.classes.CharacterRPG;
import rpg.classes.Item;
import rpg.classes.LootableItem;
import rpg.classes.Spell;
import rpg.enums.DiceType;
import rpg.main.Game;
import rpg.main.Main;
import org.fusesource.jansi.*;

import java.util.ArrayList;

public class BattleManager {

    public static void startBattle(CharacterRPG ally, CharacterRPG enemy, boolean isEscapeable){
        Main.clearScreen();
        System.out.println("\n---BATTLE START---");
        boolean isAllyTurn;
        boolean fledBattle = false;
        while(ally.isAlive && enemy.isAlive && !fledBattle){
            isAllyTurn = true;
            while(isAllyTurn){
                ally.setTemporaryArmor(0);
                System.out.println("\nYou are " + Game.ANSI_YELLOW + ally.getName() + Game.ANSI_RESET +
                        ", you are level " + ally.getLevel() +
                        ", you have " + Game.ANSI_GREEN + ally.getHP() +  "/" + ally.getMaxHP() + " HP left." + Game.ANSI_RESET +
                        "\nYou are fighting " + Game.ANSI_YELLOW + enemy.getName() + Game.ANSI_RESET +
                        ", " + enemy.getPronoun().toLowerCase() + (enemy.getPronoun().toLowerCase().equals("they") ? " are" : " is") +" level " + Main.getEnemyLevelColoration(ally, enemy) + enemy.getLevel() + Game.ANSI_RESET +
                        ", " + enemy.getPronoun().toLowerCase() + (enemy.getPronoun().toLowerCase().equals("they") ? " have " : " has ") + Game.ANSI_RED + enemy.getHP() +  "/" + enemy.getMaxHP() + " HP left." + Game.ANSI_RESET +
                        "\n\n");
                System.out.println("1) Attack\n2) Defend yourself\n3) Use a skill\n4) Use an item\n5) Flee");
                switch(Main.getChoice(1, 5)){
                    case 1:
                        Main.clearScreen();
                        ally.Attack(enemy, false);
                        isAllyTurn = false;
                        break;
                    case 2:
                        Main.clearScreen();
                        System.out.println("You brace yourself in preparation for the enemy's attack. " +
                                "(" + Game.ANSI_GREEN + "+1 Armor" + Game.ANSI_RESET + " for this turn)");
                        ally.setTemporaryArmor(1);
                        isAllyTurn = false;
                        break;
                    case 3:
                        Main.clearScreen();
                        System.out.println("   [SKILLS]   ");
                        if(ally.getSpells().isEmpty())
                            System.out.println("You don't have any skills.");
                        else{
                            int cptSpell = 1;
                            for(Spell s : ally.getSpells()){
                                System.out.println(cptSpell + ") " + s.getName());
                                cptSpell++;
                            }
                            System.out.println(cptSpell + ") Return");
                            int choiceSpell = Main.getChoice(1, cptSpell);
                            if(choiceSpell != cptSpell){ //Chose "Return"
                                Spell s = new ArrayList<>(ally.getSpells()).get(choiceSpell-1);
                                System.out.println("\nChoose the target : " +
                                        "\n1) " + ally.getName() +
                                        "\n2) " + enemy.getName());
                                if(Main.getChoice(1, 2) == 1) {
                                    Main.clearScreen();
                                    s.Cast(ally);
                                }
                                else {
                                    Main.clearScreen();
                                    s.Cast(ally, enemy);
                                }
                                isAllyTurn = false;
                            }
                            else{
                                isAllyTurn = true;
                            }

                        }
                        break;
                    case 4:
                        Main.clearScreen();
                        System.out.println("   [INVENTORY]   ");
                        if(ally.isInventoryEmpty())
                            System.out.println("You don't have any item.");
                        else{
                            int cptItem = 1;
                            for(LootableItem i : ally.getInventory().keySet()){
                                System.out.println(cptItem + ") " + i.getName());
                                cptItem++;
                            }
                            System.out.println(cptItem + ") Return");
                            int choiceItem = Main.getChoice(1, cptItem);
                            if(choiceItem != cptItem){
                                LootableItem i = new ArrayList<>(ally.getInventory().keySet()).get(choiceItem-1);
                                System.out.println("\nChoose the target : " +
                                        "\n1) " + ally.getName() +
                                        "\n2) " + enemy.getName());
                                if(Main.getChoice(1, 2) == 1){
                                    Main.clearScreen();
                                    try {
                                        ally.useItem(i);
                                        isAllyTurn = false;
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                        isAllyTurn = true;
                                    }
                                }
                                else{
                                    Main.clearScreen();
                                    try {
                                        ally.useItem(i, enemy);
                                        isAllyTurn = false;
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                        isAllyTurn = true;
                                    }
                                }
                            }
                            else{
                                isAllyTurn = true;
                            }
                        }
                        break;
                    case 5:
                        Main.clearScreen();
                        if(isEscapeable){
                            if(Main.rollDice(DiceType.D100) <= 40){
                                System.out.println("\nYou managed to escape from this battle.");
                                fledBattle = true;
                            }
                            else{
                                System.out.println("\nYou failed to escape.");
                            }
                            isAllyTurn = false;
                        }
                        else{
                            System.out.println("\nYou can't escape from this battle.");
                            isAllyTurn = true;
                        }
                        break;
                }
            }
            enemy.setTemporaryArmor(0);
            if(enemy.isAlive){
                if(Main.rollDice(DiceType.D100) >= 25){
                    enemy.Attack(ally, false);
                }
                else{
                    System.out.println(Game.ANSI_YELLOW + enemy.getName() + Game.ANSI_RESET + " takes a defensive posture and waits for your next attack.");
                    enemy.setTemporaryArmor(1);
                }
            }
        }
    }

}
