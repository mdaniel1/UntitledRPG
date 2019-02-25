package rpg.classes;

import rpg.enums.DiceType;
import rpg.enums.SpellType;
import rpg.main.Game;
import rpg.main.Main;

public class SpellList {

    public static final Spell HEAL_BASIC = new WaterSpell("Light heal", "W_SPELL_LIGHT_HEAL", DiceType.D4, SpellType.HEAL){

        @Override
        public void effects(CharacterRPG c1){
            int diceRoll = Main.rollDice(DiceType.D20);
            int restoredHP = Main.rollDice(getDice()) + c1.getBonusHeal();

            switch(diceRoll){
                case 1:
                    System.out.println(Main.getFumbleDescriptor(true) + Game.ANSI_YELLOW + c1.getName() + Game.ANSI_RESET
                            + " tried to cast " + this.getName() + " but failed.\n"
                            + Game.ANSI_RED + "No health restored." + Game.ANSI_RESET);
                    break;
                case 20:
                    restoredHP *= 2;
                    System.out.println(Main.getCriticalDescriptor(true) + Game.ANSI_YELLOW + c1.getName() + Game.ANSI_RESET
                            + " casts " + this.getName() + " and restores "
                            + Game.ANSI_GREEN + restoredHP + " health." + Game.ANSI_RESET);
                    c1.setHP(c1.getHP() + restoredHP);
                    break;
                default:
                    if(c1.getHP() == c1.getMaxHP()){
                        System.out.println(Game.ANSI_YELLOW + c1.getName() + Game.ANSI_RESET
                                + " casts " + this.getName() + " but is already healthy.");
                    }
                    else if(c1.getHP() + restoredHP >= c1.getMaxHP()){
                        System.out.println(Game.ANSI_YELLOW + c1.getName() + Game.ANSI_RESET
                                + " casts " + this.getName() + " and is restored to full life.");
                        c1.setHP(c1.getMaxHP());
                    }
                    else{
                        System.out.println(Game.ANSI_YELLOW + c1.getName() + Game.ANSI_RESET
                                + " casts " + this.getName() + " and restores "
                                + Game.ANSI_GREEN + restoredHP + " health." + Game.ANSI_RESET);
                        c1.setHP(c1.getHP() + restoredHP);
                    }

                    break;
            }
        }

        @Override
        public void effects(CharacterRPG c1, CharacterRPG c2) {
            int diceRoll = Main.rollDice(DiceType.D20);
            int restoredHP = Main.rollDice(getDice()) + c1.getBonusHeal();

            switch(diceRoll){
                case 1:
                    System.out.println(Main.getFumbleDescriptor(true) + Game.ANSI_YELLOW + c1.getName() + Game.ANSI_RESET
                            + " tried to cast " + this.getName() + " on " + Game.ANSI_YELLOW + c2.getName() + Game.ANSI_RESET
                            + "but failed.\n"
                            + Game.ANSI_RED + "No health restored." + Game.ANSI_RESET);
                    break;
                case 20:
                    restoredHP *= 2;
                    System.out.println(Main.getCriticalDescriptor(true) + Game.ANSI_YELLOW + c1.getName() + Game.ANSI_RESET
                            + " casts " + this.getName() + " on " + Game.ANSI_YELLOW + c2.getName() + Game.ANSI_RESET + " and restores "
                            + Game.ANSI_GREEN + restoredHP + " health." + Game.ANSI_RESET);
                    c2.setHP(c2.getHP() + restoredHP);
                    break;
                default:
                    if(c2.getHP() == c2.getMaxHP()){
                        System.out.println(Game.ANSI_YELLOW + c1.getName() + Game.ANSI_RESET
                                + " casts " + this.getName() + " on " + Game.ANSI_YELLOW + c2.getName() + Game.ANSI_RESET
                                + " but " + Game.ANSI_YELLOW + c2.getName() + Game.ANSI_RESET + " is already healthy.");
                    }
                    else if(c2.getHP() + restoredHP >= c1.getMaxHP()){
                        System.out.println(Game.ANSI_YELLOW + c1.getName() + Game.ANSI_RESET
                                + " casts " + this.getName() + " on " + Game.ANSI_YELLOW + c2.getName() + Game.ANSI_RESET
                                + " and " + Game.ANSI_YELLOW + c2.getName() + Game.ANSI_RESET + " is restored to full life.");
                        c2.setHP(c2.getMaxHP());
                    }
                    else{
                        System.out.println(Game.ANSI_YELLOW + c1.getName() + Game.ANSI_RESET
                                + " casts " + this.getName() + " on " + Game.ANSI_YELLOW + c2.getName() + Game.ANSI_RESET + " and restores "
                                + Game.ANSI_GREEN + restoredHP + " health." + Game.ANSI_RESET + " to " + Game.ANSI_YELLOW + c2.getName() + Game.ANSI_RESET);
                        c2.setHP(c2.getHP() + restoredHP);
                    }
                    break;
            }
        }
    };

}
