package rpg.classes;

public class Item {
    private String name;
    private String rawName;
    private String article;
    private int basePrice;

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

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }

    public void applyEffects(CharacterRPG c1) {

    }

    public void applyEffects(CharacterRPG c1, CharacterRPG c2) {

    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Item && (this.getRawName().equals(((Item) obj).getRawName()));
    }
}
