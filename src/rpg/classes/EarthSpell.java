package rpg.classes;

import rpg.enums.DiceType;
import rpg.enums.SpellType;
import org.fusesource.jansi.*;

public class EarthSpell extends Spell {
    public EarthSpell(String name, String rawName, DiceType dice, SpellType type) {
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
