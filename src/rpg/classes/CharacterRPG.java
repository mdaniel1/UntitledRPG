package rpg.classes;

import rpg.enums.DiceType;
import rpg.enums.SpellType;
import rpg.main.Game;
import rpg.main.Main;

import java.util.HashSet;

public class CharacterRPG {

    //PRIMARY CHARACTERISTICS
    private String name;
    private int HP;
    private int maxHP;
    private Weapon weapon;
    private int armor;
    private int level = 1;
    boolean isAlive = true;

    //SECONDARY CHARACTERISTICS
    private int bonusDamage = 0;
    private int bonusHeal = 0;
    private int bonusHitChance = 0;
    private String pronoun; //He, she, they, etc...
    private String article; //a, the, ...
    private HashSet<Spell> spells = new HashSet<>();

    public CharacterRPG(String name, int HP, int maxHP, int armor, String pronoun, String article){
        setName(name);
        setHP(HP);
        setMaxHP(maxHP);
        setArmor(armor);
        setPronoun(pronoun);
        setArticle(article);
        setWeapon(new Weapon("Fists", DiceType.D4, 20, 1));
    }


    String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    int getHP() {
        return HP;
    }

    void setHP(int HP) {
        this.HP = HP;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    private int getArmor() {
        return armor;
    }

    void setArmor(int armor) {
        this.armor = armor;
    }

    int getLevel() {
        return level;
    }

    void setLevel(int level) {
        this.level = level;
    }

    int getMaxHP() {
        return maxHP;
    }

    void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getBonusHeal() {
        return bonusHeal;
    }

    public void setBonusHeal(int bonusHeal) {
        this.bonusHeal = bonusHeal;
    }

    public int getBonusDamage() {
        return bonusDamage;
    }

    public void setBonusDamage(int bonusDamage) {
        this.bonusDamage = bonusDamage;
    }

    public int getBonusHitChance() {
        return bonusHitChance;
    }

    public void setBonusHitChance(int bonusHitChance) {
        this.bonusHitChance = bonusHitChance;
    }

    public String getPronoun() {
        return pronoun;
    }

    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    void Attack(CharacterRPG c, boolean forceFumble){
        if(c != null){
            if(c != this){
                int diceRoll = Main.rollDice(DiceType.D20) + this.bonusHitChance;
                if(diceRoll != 1 && !forceFumble){
                    if(diceRoll < this.getWeapon().getCritValue()){
                        if(c.getArmor() <= diceRoll) //HIT
                        {
                            int damageRoll = this.getWeapon().getDamage() + this.bonusDamage;
                            System.out.println(Game.ANSI_YELLOW + getName() + Game.ANSI_RESET
                                    + " managed to hit " + Game.ANSI_YELLOW + c.getName() + Game.ANSI_RESET + " with " + getArticle()
                                    + Game.ANSI_YELLOW + this.getWeapon().getName() + Game.ANSI_RESET
                                    + " for " + Game.ANSI_RED + damageRoll + Game.ANSI_RESET + " damage.");
                            calculateDamage(c, damageRoll);
                        }
                        else{ //MISS
                            System.out.println(this.getName() + " misses " + c.getName() + " and inflicts no damage ");
                        }
                    }
                    else{ //CRIT
                        int damageRoll = this.getWeapon().getDamage() + this.bonusDamage;
                        damageRoll*=2;
                        System.out.println(Game.ANSI_YELLOW + "CRITICAL ! " + Game.ANSI_RESET + this.getName()
                                + " managed to hit " + c.getName() + " for " + Game.ANSI_RED + damageRoll + Game.ANSI_RESET + " damage.");
                        calculateDamage(c, damageRoll);
                    }
                }
                else{ //FUMBLE (oh no)
                    int damageRoll = this.getWeapon().getDamage() + this.bonusDamage;
                    System.out.println(Game.ANSI_RED + "FUMBLE ! " + this.getName() + " hits themselves for " + damageRoll + " damage." + Game.ANSI_RESET);
                    calculateDamage(this, damageRoll);
                }
            }
            else{
                System.out.println(this.getName() + " can't attack themselves on purpose, that would be silly.");
            }
        }
    }

    private void calculateDamage(CharacterRPG c, int damageRoll) {
        c.setHP(c.getHP() - damageRoll);
        if(c.getHP() <= 0){
            System.out.println(c.getName() + " is dead.");
            c.isAlive = false;
        }
    }

    public boolean hasSpell(String rawName){
        for(Spell s : spells){
            if(s.getRawName().equals(rawName))
                return true;
        }

        return false;
    }

    public boolean hasAnySpell(SpellType type){
        for(Spell s : spells){
            if(s.getType() == type)
                return true;
        }

        return false;
    }

    void LevelUp(int choice){

    }
}

