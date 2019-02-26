package rpg.classes;

import rpg.main.Game;

public class ItemList {
    public static final Item SMALL_HEALTH_POTION = new Item("small health potion", "I_S_HEALTH_POTION", "a", 8){
        int restoredHP = 12;

        @Override
        public void applyEffects(CharacterRPG c1) {
            if(c1.getHP() == c1.getMaxHP()){
                System.out.println(Game.ANSI_YELLOW + c1.getName() + Game.ANSI_RESET
                        + " uses " + Game.ANSI_PURPLE + this.getArticle() + " " + this.getName() + Game.ANSI_RESET + " but is already healthy.");
            }
            else if(c1.getHP() + restoredHP >= c1.getMaxHP()){
                System.out.println(Game.ANSI_YELLOW + c1.getName() + Game.ANSI_RESET
                        + " uses " + Game.ANSI_PURPLE + this.getArticle() + " " + this.getName() + Game.ANSI_RESET + " and is restored to full life.");
                c1.setHP(c1.getMaxHP());
            }
            else{
                System.out.println(Game.ANSI_YELLOW + c1.getName() + Game.ANSI_RESET
                        + " uses " + Game.ANSI_PURPLE + this.getArticle() + " " + this.getName() + Game.ANSI_RESET + " and restores "
                        + Game.ANSI_GREEN + restoredHP + " HP." + Game.ANSI_RESET);
                c1.setHP(c1.getHP() + restoredHP);
            }
        }

        @Override
        public void applyEffects(CharacterRPG c1, CharacterRPG c2) {
            if(c2.getHP() == c2.getMaxHP()){
                System.out.println(Game.ANSI_YELLOW + c1.getName() + Game.ANSI_RESET
                        + " uses " + Game.ANSI_PURPLE + this.getArticle() + " " + this.getName() + Game.ANSI_RESET
                        + " on " + Game.ANSI_YELLOW + c2.getName() + Game.ANSI_RESET
                        + " but " + Game.ANSI_YELLOW + c2.getName() + Game.ANSI_RESET + " is already healthy.");
            }
            else if(c2.getHP() + restoredHP >= c1.getMaxHP()){
                System.out.println(Game.ANSI_YELLOW + c1.getName() + Game.ANSI_RESET
                        + " uses " + Game.ANSI_PURPLE + this.getArticle() + " " + this.getName() + Game.ANSI_RESET
                        + " on " + Game.ANSI_YELLOW + c2.getName() + Game.ANSI_RESET
                        + " and " + Game.ANSI_YELLOW + c2.getName() + Game.ANSI_RESET + " is restored to full life.");
                c2.setHP(c2.getMaxHP());
            }
            else{
                System.out.println(Game.ANSI_YELLOW + c1.getName() + Game.ANSI_RESET
                        + " uses " + Game.ANSI_PURPLE + this.getArticle() + " " + this.getName() + Game.ANSI_RESET
                        + " on " + Game.ANSI_YELLOW + c2.getName() + Game.ANSI_RESET
                        + " and " + Game.ANSI_YELLOW + c2.getName() + Game.ANSI_RESET + " restores "
                        + Game.ANSI_GREEN + restoredHP + " HP.");
                c2.setHP(c2.getHP() + restoredHP);
            }
        }
    };
}
