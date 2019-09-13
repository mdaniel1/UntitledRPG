package rpg.classes;

public class Item implements LootableItem{
    private String name;
    private String article;
    private int basePrice;
    private String rawName = null;

    public Item(String name, String rawName, String article, int basePrice){
        setArticle(article);
        setBasePrice(basePrice);
        setName(name);
        setRawName(rawName);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public void applyEffects(CharacterRPG c1) {

    }

    public void applyEffects(CharacterRPG c1, CharacterRPG c2) {

    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Item && (this.getRawName().equals(((Item) obj).getRawName()));
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
