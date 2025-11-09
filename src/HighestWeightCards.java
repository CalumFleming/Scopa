import java.util.ArrayList;

public class HighestWeightCards {
    ArrayList<Card> cards = new ArrayList<>();
    int totalWeight = 0;

    public HighestWeightCards(){
        this.cards = cards;
        this.totalWeight = totalWeight;
    }

    public void addCard(Card card){
        this.cards.add(card);
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public int getTotalWeight() {
        return totalWeight;
    }
}
