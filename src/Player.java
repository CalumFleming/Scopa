import java.util.ArrayList;

public class Player {
    protected String name;
    protected ArrayList<Card> hand;
    protected int roundPoints;

    public Player(String name, ArrayList<Card> hand, int roundPoints) {
        this.name = name;
        this.hand = hand;
        this.roundPoints = roundPoints;
    }

    public String getName() {
        return name;
    }
    
    public Card getCardFromHand(int index) {
        return hand.get(index);
    }
    
    public int getRoundPoints() {
        return roundPoints;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void addRoundPoints(int points) {
        this.roundPoints += points;
    }
}
