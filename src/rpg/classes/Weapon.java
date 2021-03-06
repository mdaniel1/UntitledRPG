package rpg.classes;

import rpg.enums.DiceType;
import rpg.main.Main;

public class Weapon implements LootableItem {
    private String name;
    private DiceType damage;
    private int critValue;
    private int nbAttacks;
    private String rawName = null;

    public Weapon(String name, DiceType damage, int crit, int nbAttacks) {
        setName(name);
        setDamage(damage);
        setCritValue(crit);
    }

    public String getName() {
        return name;
    }

    @Override
    public String getArticle() {
        return "the";
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return Main.rollDice(damage);
    }

    public int getMaxDamage(){ return damage.getValue(); }

    public void setDamage(DiceType damage) {
        this.damage = damage;
    }

    public int getCritValue() {
        return critValue;
    }

    public void setCritValue(int critValue) {

        if(critValue < 0 || critValue > 20)
            this.critValue = 20;
        else
            this.critValue = critValue;
    }

    public int getNbAttacks() {
        return nbAttacks;
    }

    public void setNbAttacks(int nbAttacks) {
        this.nbAttacks = nbAttacks;
    }

    @Override
    public String getRawName() {
        return rawName;
    }

    @Override
    public void setRawName(String rawName) {
        this.rawName = rawName;
    }
}
