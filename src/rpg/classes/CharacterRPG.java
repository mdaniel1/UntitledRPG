package rpg.classes;

import rpg.enums.DiceType;
import rpg.enums.SpellType;
import rpg.exceptions.NoSuchItemException;
import rpg.main.Game;
import rpg.main.Main;

import java.util.ArrayList;
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
    public boolean isAlive = true;
    private int money = 0;

    private HashSet<Spell> spells = new HashSet<>();

    private HashMap<LootableItem, Integer> inventory = new HashMap<>();
    //SECONDARY CHARACTERISTICS

    private int bonusDamage = 0;
    private int bonusHeal = 0;
    private int bonusHitChance = 0;
    private int temporaryArmor = 0;

    private String pronoun; //He, she, they, etc...

    private String article; //a, the, ...
    private ArrayList<LootableItem> lootTable = new ArrayList<>();

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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }
    public void setHP(int HP) {
        this.HP = HP;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getTemporaryArmor() {
        return temporaryArmor;
    }

    public void setTemporaryArmor(int temporaryArmor) {
        this.temporaryArmor = temporaryArmor;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxHP() {
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
        if(article.isEmpty())
        {
            this.article = article;
        }
        else{
            this.article = article + " ";
        }
    }

    public ArrayList<LootableItem> getLootTable() {
        return lootTable;
    }

    public void setLootTable(ArrayList<LootableItem> lootTable) {
        this.lootTable = lootTable;
    }

    public int getInternalId() {
        return internalId;
    }

    public void setInternalId(int internalId) {
        this.internalId = internalId;
    }

    public String getPresentConjugation(String verb){
        switch(verb){
            case "be":
                if(getPronoun().toLowerCase().equals("he") || getPronoun().toLowerCase().equals("she") || getPronoun().toLowerCase().equals("it"))
                {
                    return "is";
                }
                else{
                    return "are";
                }
            case "have":
                if(getPronoun().toLowerCase().equals("he") || getPronoun().toLowerCase().equals("she") || getPronoun().toLowerCase().equals("it"))
                {
                    return "has";
                }
                else{
                    return "have";
                }
            case "go":
                if(getPronoun().toLowerCase().equals("he") || getPronoun().toLowerCase().equals("she") || getPronoun().toLowerCase().equals("it"))
                {
                    return "goes";
                }
                else{
                    return "go";
                }
            default:
                if(getPronoun().toLowerCase().equals("he") || getPronoun().toLowerCase().equals("she") || getPronoun().toLowerCase().equals("it"))
                {
                    return verb + "s";
                }
                else{
                    return verb;
                }
        }
    }

    public String getReflexivePronoun(boolean isPlural){
        switch(getPronoun().toLowerCase()){
            case "he":
                return "himself";
            case "she":
                return "herself";
            case "it":
                return "itself";
            case "we":
                return "ourselves";
            case "you":
                if(isPlural)
                    return "yourselves";
                else
                    return "yourself";
            default:
                return "themselves";
        }
    }

    public void Attack(CharacterRPG enemy, boolean forceFumble){
        if(enemy != null){
            if(enemy != this){
                int diceRoll = Main.rollDice(DiceType.D20) + this.bonusHitChance;
                if(diceRoll != 1 && !forceFumble){
                    if(diceRoll < this.getWeapon().getCritValue()){
                        if(enemy.getArmor() + enemy.temporaryArmor <= diceRoll) //HIT
                        {
                            int damageRoll = this.getWeapon().getDamage() + this.bonusDamage;
                            System.out.println(getArticle() + Game.ANSI_YELLOW + getName() + Game.ANSI_RESET
                                    + " managed to hit " + Game.ANSI_YELLOW + enemy.getName() + Game.ANSI_RESET + " with "
                                    + Game.ANSI_YELLOW + this.getWeapon().getName() + Game.ANSI_RESET
                                    + " for " + Game.ANSI_RED + damageRoll + Game.ANSI_RESET + " damage.");
                            calculateDamage(enemy, damageRoll);
                        }
                        else{ //OR MISS, I GUESS THEY NEVER MISS HUH.
                            System.out.println(Game.ANSI_YELLOW + getName() + Game.ANSI_RESET
                                    + " misses "
                                    + Game.ANSI_YELLOW + enemy.getName() + Game.ANSI_RESET
                                    + " and inflicts no damage ");
                        }
                    }
                    else{ //CRIT
                        int damageRoll = this.getWeapon().getDamage() + this.bonusDamage;
                        damageRoll*=2;
                        System.out.println(Main.getCriticalDescriptor() + Game.ANSI_YELLOW + getName() + Game.ANSI_RESET
                                + " managed to hit " + Game.ANSI_YELLOW + enemy.getName() + Game.ANSI_RESET
                                + " for " + Game.ANSI_RED + damageRoll + Game.ANSI_RESET + " damage.");

                        calculateDamage(enemy, damageRoll);
                    }
                }
                else{ //FUMBLE (oh no)
                    int damageRoll = this.getWeapon().getDamage() + this.bonusDamage;
                    System.out.println(Main.getFumbleDescriptor() + Game.ANSI_YELLOW + getName() + Game.ANSI_RESET
                            + " hits " + getReflexivePronoun(false) + " for "
                            +Game.ANSI_RED + damageRoll + Game.ANSI_RESET + " damage.");

                    calculateDamage(this, damageRoll);
                }
            }
            else{
                System.out.println(this.getName() + " can't attack themselves on purpose, that would be silly.");
            }
        }
    }

    private void calculateDamage(CharacterRPG character, int damageRoll) {
        character.setHP(character.getHP() - damageRoll);
        if(character.getHP() <= 0){
            System.out.println(Game.ANSI_YELLOW + character.getName() + Game.ANSI_RESET + " is dead.");
            character.isAlive = false;
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

    public void addSpell(Spell s){
        try{
            System.out.println(Game.ANSI_YELLOW + this.getName() + Game.ANSI_RESET + " learned " + Game.ANSI_CYAN + s.getName() + Game.ANSI_RESET);
            spells.add(s);
        }
        catch(Exception e){

        }
    }

    public HashSet<Spell> getSpells() {
        return spells;
    }

    public HashMap<LootableItem, Integer> getInventory(){
        return inventory;
    }

    public boolean hasItem(String rawName){
        for(LootableItem i : inventory.keySet()){
            if(i.getRawName().equals(rawName))
                return true;
        }

        return false;
    }

    public boolean hasItem(LootableItem item){
        return inventory.containsKey(item);
    }

    public boolean isInventoryEmpty(){
        return inventory.isEmpty();
    }

    public LootableItem getItem(String rawName) throws NoSuchItemException {
        for(LootableItem i : inventory.keySet()){
            if(i.getRawName().equals(rawName))
                return i;
        }

        throw new NoSuchItemException("Not enough " + rawName);
    }

    public LootableItem getItem(LootableItem item) throws NoSuchItemException {
        for(LootableItem i : inventory.keySet()){
            if(i.equals(item))
                return i;
        }

        throw new NoSuchItemException("Not enough " + item.getName() + ("s"));
    }

    public void addItem(LootableItem i){
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

    public void useItem(LootableItem i) throws Exception {
        if(i instanceof Weapon)
            throw new Exception("This item cannot be used.");
        else{
            if(hasItem((Item)i)){
                int nb = inventory.get(i);
                nb -= 1;
                ((Item) i).applyEffects(this);
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
    }

    public void useItem(LootableItem i, CharacterRPG target) throws Exception {
        if(i instanceof Weapon)
            throw new Exception("This item cannot be used.");
        if(hasItem(i)){
            int nb = inventory.get(i);
            nb--;
            ((Item) i).applyEffects(this, target);
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

    public LootableItem getRandomizedLoot() throws NoSuchItemException {
        if(!lootTable.isEmpty()){
            int indexOfLootedItem = Main.dice.nextInt(lootTable.size());
            LootableItem lootedItem = lootTable.get(indexOfLootedItem);
            lootTable.remove(indexOfLootedItem);
            return lootedItem;
        }
        throw new NoSuchItemException("There is no more item to loot.");
    }

    public void addItemToLootTable(LootableItem i){
        if(i != null)
        {
            lootTable.add(i);
        }
    }

    void LevelUp(int choice){

    }
}

