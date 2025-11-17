import java.util.ArrayList;

public class HighestWeightCards {
    ArrayList<Card> cards = new ArrayList<>();
    Card handCard;
    int totalWeight = 0;

    public HighestWeightCards(){
        this.cards = cards;
        this.handCard = handCard;
        this.totalWeight = totalWeight;
    }

    public void addCard(Card card){
        this.cards.add(card);
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public Card getHandCard() {
        return handCard;
    }

    public void setHandCard(Card handCard) {
        this.handCard = handCard;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public static int getCardsWeight(ArrayList<Card> cards){
        int totalWeight = 0;
        for(Card card : cards){
            totalWeight += card.getWeight();
        }
        return totalWeight;
    }
}
