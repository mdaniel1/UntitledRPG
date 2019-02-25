package rpg.enums;

public enum DiceType {
    D100(100),
    D20(20),
    D12(12),
    D10(10),
    D8(8),
    D6(6),
    D4(4),
    D2(2);

    private final int value;
    DiceType(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
