package rpg.classes;

import rpg.enums.DiceType;
import rpg.enums.SpellType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class WaterSpell extends Spell {
    public WaterSpell(String name, String rawName, DiceType dice, SpellType type) {
        setName(name);
        setRawName(rawName);
        setDice(dice);
        setType(type);
    }

    @Override
    public void effects(CharacterRPG c1) {
        throw new NotImplementedException();
        //TODO
    }

    @Override
    public void effects(CharacterRPG c1, CharacterRPG c2) {
        throw new NotImplementedException();
        //TODO
    }
}
