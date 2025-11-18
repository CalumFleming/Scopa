import java.util.ArrayList;
import java.util.Comparator;

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
        ArrayList<Card> tempCoins = new ArrayList<>();
        for(Card card:takenCards){
            if(card.getSuit().equals("Coins")){
                tempCoins.add(card);
            }
        }
        tempCoins.sort(Comparator.comparingInt(Card::getPrimeraValue).reversed());

        ArrayList<Card> tempSwords = new ArrayList<>();
        for(Card card:takenCards){
            if(card.getSuit().equals("Swords")){
                tempSwords.add(card);
            }
        }
        tempSwords.sort(Comparator.comparingInt(Card::getPrimeraValue).reversed());

        ArrayList<Card> tempCups = new ArrayList<>();
        for(Card card:takenCards){
            if(card.getSuit().equals("Cups")){
                tempCups.add(card);
            }
        }
        tempCups.sort(Comparator.comparingInt(Card::getPrimeraValue).reversed());

        ArrayList<Card> tempClubs = new ArrayList<>();
        for(Card card:takenCards){
            if(card.getSuit().equals("Clubs")){
                tempClubs.add(card);
            }
        }
        tempClubs.sort(Comparator.comparingInt(Card::getPrimeraValue).reversed());

        if(tempCoins.size() == 0 | tempSwords.size() == 0 | tempCups.size() == 0 | tempClubs.size() == 0){
            System.out.println("Not all suits present");
            primeraScore = 0;
        } else {
            primeraScore = tempCoins.getFirst().getPrimeraValue() + tempSwords.getFirst().getPrimeraValue() + tempCups.getFirst().getPrimeraValue() + tempClubs.getFirst().getPrimeraValue();
        }

    }

    public void calculateNumberOfCoins() {
        numberOfCoins = 0;
        for(Card card:takenCards){
            if(card.getSuit().equals("Coins")){
                numberOfCoins++;
            }
        }
    }
}
