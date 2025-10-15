public class Card {
    int id;
    String name;
    String suit;
    int value;
    boolean selected;

    public Card(int id, String name, String suit, int value) {
        this.id=id;
        this.name=name;
        this.suit=suit;
        this.value=value;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSuit() {
        return suit;
    }
    public int getValue() {
        return value;
    }


}
