package rpg.classes;

import rpg.enums.DiceType;
import rpg.enums.SpellType;

public class FireSpell extends Spell {
    public FireSpell(String name, String rawName, DiceType dice, SpellType type) {
        setName(name);
        setRawName(rawName);
        setDice(dice);
        setType(type);
    }

    @Override
    public void effects(CharacterRPG c1) {

    }

    @Override
    public void effects(CharacterRPG c1, CharacterRPG c2) {

    }
}
