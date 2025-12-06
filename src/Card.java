public class Card {
    int id;
    String name;
    String suit;
    int value;
    int primeraValue;
    int weight;
    String imagePath;

    public Card(int id, String name, String suit, int value, int primeraValue, int weight, String imagePath) {
        this.id=id;
        this.name=name;
        this.suit=suit;
        this.value=value;
        this.primeraValue=primeraValue;
        this.weight=weight;
        this.imagePath=imagePath;
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
    public int getPrimeraValue() {
        return primeraValue;
    }

    public void setPrimeraValue(int primeraValue) {
        this.primeraValue = primeraValue;
    }

    public int getWeight() {
        return weight;
    }

    public String getImagePath(){
        return imagePath;
    }
}
  