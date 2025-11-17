import java.util.ArrayList;

public class Player {
    protected String name;
    protected ArrayList<Card> hand;
    protected ArrayList<Card> takenCards;
    protected int totalPoints;
    protected int numberOfCoins;
    protected int numberOfCards;
    protected int primeraScore;

    public Player(String name, ArrayList<Card> hand, int roundPoints) {
        this.name = name;
        this.hand = hand;
        this.takenCards = new ArrayList<>();
        this.totalPoints = roundPoints;
        this.numberOfCoins = 0;
        this.numberOfCards = 0;
        this.primeraScore = 0;
    }

    public String getName() {
        return name;
    }
    
    public Card getCardFromHand(int index) {
        return hand.get(index);
    }
    
    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void addRoundPoints(int points) {
        this.totalPoints += points;
    }

    public ArrayList<Card> getTakenCards() {
        return takenCards;
    }

    public void setNumberOfCoins(int numberOfCoins) {
        this.numberOfCoins = numberOfCoins;
    }

    public int getNumberOfCoins() {
        return numberOfCoins;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    public int getPrimeraScore() {
        return primeraScore;
    }

    public void setPrimeraScore(int primeraScore) {
        this.primeraScore = primeraScore;
    }

    public void calculatePrimeraScore() {
        // TODO: calculate this here
    }
}
