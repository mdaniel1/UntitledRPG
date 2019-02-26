package rpg.classes;

import rpg.enums.DiceType;
import rpg.enums.SpellType;

public abstract class Spell {
    private String name;
    private String rawName;
    private DiceType dice;
    private SpellType type;

    public abstract void effects(CharacterRPG c1);
    public abstract void effects(CharacterRPG c1, CharacterRPG c2);


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }

    public DiceType getDice() {
        return dice;
    }

    public void setDice(DiceType dice) {
        this.dice = dice;
    }

    public SpellType getType() {
        return type;
    }

    public void Cast(CharacterRPG c1){
        effects(c1);
    }

    public void Cast(CharacterRPG c1, CharacterRPG c2){
        effects(c1, c2);
    }

    public void setType(SpellType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Spell && (this.getRawName().equals(((Spell) obj).getRawName()));
    }
}
