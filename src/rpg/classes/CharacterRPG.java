package rpg.classes;

import rpg.enums.DiceType;
import rpg.enums.SpellType;
import rpg.exceptions.NoSuchItemException;
import rpg.main.Game;
import rpg.main.Main;

import java.util.HashMap;
import java.util.HashSet;

public class CharacterRPG {

    //PRIMARY CHARACTERISTICS
    private static int nextId = 0;
    private int internalId;
    private String name;
    private int HP;
    private int maxHP;
    private Weapon weapon;
    private int armor;
    private int level = 1;
    boolean isAlive = true;
    private int money = 0;
    private HashSet<Spell> spells = new HashSet<Spell>();
    private HashMap<Item, Integer> inventory = new HashMap<Item, Integer>();

    //SECONDARY CHARACTERISTICS
    private int bonusDamage = 0;
    private int bonusHeal = 0;
    private int bonusHitChance = 0;
    private String pronoun; //He, she, they, etc...
    private String article; //a, the, ...


    public CharacterRPG(String name, int HP, int maxHP, int armor, String pronoun, String article){
        setName(name);
        setHP(HP);
        setMaxHP(maxHP);
        setArmor(armor);
        setPronoun(pronoun);
        setArticle(article);
        setWeapon(new Weapon("Fists", DiceType.D4, 20, 1));
        setInternalId(++nextId);
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
                        else{ //OR MISS, I GUESS THEY NEVER MISS HUH.
                            System.out.println(Game.ANSI_YELLOW + getName() + Game.ANSI_RESET
                                    + " misses "
                                    + Game.ANSI_YELLOW + c.getName() + Game.ANSI_RESET
                                    + " and inflicts no damage ");
                        }
                    }
                    else{ //CRIT
                        int damageRoll = this.getWeapon().getDamage() + this.bonusDamage;
                        damageRoll*=2;
                        System.out.println(Main.getCriticalDescriptor() + Game.ANSI_YELLOW + getName() + Game.ANSI_RESET
                                + " managed to hit " + Game.ANSI_YELLOW + c.getName() + Game.ANSI_RESET
                                + " for " + Game.ANSI_RED + damageRoll + Game.ANSI_RESET + " damage.");

                        calculateDamage(c, damageRoll);
                    }
                }
                else{ //FUMBLE (oh no)
                    int damageRoll = this.getWeapon().getDamage() + this.bonusDamage;
                    System.out.println(Main.getFumbleDescriptor() + Game.ANSI_YELLOW + getName() + Game.ANSI_RESET
                            + " hits themselves for "
                            +Game.ANSI_RED + damageRoll + Game.ANSI_RESET + " damage.");

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
            System.out.println(Game.ANSI_YELLOW + c.getName() + Game.ANSI_RESET + " is dead.");
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

    public boolean hasSpell(Spell spell){
        for(Spell s : spells){
            if(s.equals(spell))
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

    public Spell getSpell(Spell spell){
        for(Spell s : spells){
            if(s.equals(spell))
                return s;
        }
        return null;
    }

    public Spell getSpell(String rawName){
        for(Spell s : spells){
            if(s.getRawName().equals(rawName))
                return s;
        }
        return null;
    }

    public void AddSpell(Spell s){
        try{
            System.out.println(Game.ANSI_YELLOW + this.getName() + Game.ANSI_RESET + " learned " + Game.ANSI_CYAN + s.getName() + Game.ANSI_RESET);
            spells.add(s);
        }
        catch(Exception e){

        }
    }

    public boolean hasItem(String rawName){
        for(Item i : inventory.keySet()){
            if(i.getRawName().equals(rawName))
                return true;
        }

        return false;
    }

    public boolean hasItem(Item item){
        return inventory.containsKey(item);
    }

    public boolean isInventoryEmpty(){
        return inventory.size() == 0;
    }

    public Item getItem(String rawName) throws NoSuchItemException {
        for(Item i : inventory.keySet()){
            if(i.getRawName().equals(rawName))
                return i;
        }

        throw new NoSuchItemException("Not enough " + rawName);
    }

    public Item getItem(Item item) throws NoSuchItemException {
        for(Item i : inventory.keySet()){
            if(i.equals(item))
                return i;
        }

        throw new NoSuchItemException("Not enough " + item.getName() + ("s"));
    }

    public void addItem(Item i){
        System.out.println(Game.ANSI_YELLOW + this.getName() + Game.ANSI_RESET + " received "
                + Game.ANSI_PURPLE + i.getArticle() + " " + i.getName() + Game.ANSI_RESET);
        if(hasItem(i)){
            int nb = inventory.get(i);
            inventory.put(i, ++nb);
        }
        else{
            inventory.put(i, 1);
        }
    }

    public void useItem(Item i){
        if(hasItem(i)){
            int nb = inventory.get(i);
            nb -= 1;
            i.applyEffects(this);
            if(nb <= 0){
                inventory.remove(i);
            }
            else{
                inventory.put(i, nb);
            }
        }
        else
            System.out.println(Game.ANSI_YELLOW + this.getName() + Game.ANSI_RESET + " doesn't have any " + Game.ANSI_PURPLE + i.getName() + Game.ANSI_RESET);
    }

    public void useItem(Item i, CharacterRPG target){
        if(hasItem(i)){
            int nb = inventory.get(i);
            nb--;
            i.applyEffects(this, target);
            if(nb <= 0){
                inventory.remove(i);
            }
            else{
                inventory.put(i, nb);
            }
        }
        else
            System.out.println(Game.ANSI_YELLOW + this.getName() + Game.ANSI_RESET + " doesn't have any " + Game.ANSI_PURPLE + i.getName() + Game.ANSI_RESET);
    }

    void LevelUp(int choice){

    }

    public int getInternalId() {
        return internalId;
    }

    public void setInternalId(int internalId) {
        this.internalId = internalId;
    }
}

