public class Player {
    private String name;
    private Card[] hand;
    private int roundPoints;

    public Player(String name, Card[] hand, int roundPoints) {
        this.name = name;
        this.hand = hand;
        this.roundPoints = roundPoints;
    }

    public String getName() {
        return name;
    }
    public Card getCardFromHand(int index) {
        return hand[index];
    }
    public int getRoundPoints() {
        return roundPoints;
    }

    public void setHand(Card[] hand) {
        this.hand = hand;
    }

    public Card[] getHand() {
        return hand;
    }

    public void addRoundPoints(int points) {
        this.roundPoints += points;
    }


}
