import java.util.*;

public class Game {
    Deck deck = new Deck();
    int roundNumber = 1;
    ArrayList<Card> initialHand = new ArrayList<>();
    int initialRoundScore = 0;
    int handNumber = 1;
    Player humanPlayer = new Player("Human", initialHand, initialRoundScore);
    AIPlayer aIPlayer = new AIPlayer("AI", new ArrayList<>(), initialRoundScore);
    ArrayList<Card> boardCards = new ArrayList<Card>();
    private ArrayList<Card> boardSelectedCards = new ArrayList<Card>();
    private Card handSelectedCard;
    private Card aIHandSelectedCard;
    private int playerValueOfPlay = 0;
    private int playerValueOfBoard = 0;

    BoardGUI boardGUI = new BoardGUI(this);

    public void newGame() {
        deck.shuffleDeck();

        humanPlayer.setHand(new ArrayList<>(Arrays.asList(deck.dealCards())));
        aIPlayer.setHand(new ArrayList<>(Arrays.asList(deck.dealCards())));
        
        Card[] tempHand = deck.dealBoardCards();
        for(Card card:tempHand){
            boardCards.add(card);
        }

        for (int i = 0; i < humanPlayer.getHand().size(); i++) {
            boardGUI.addHandCard(humanPlayer.getHand().get(i));
        }
        for (int i = 0; i < boardCards.size(); i++) {
            boardGUI.addBoardCard(boardCards.get(i));
        }
        for(Card card:boardCards){
            System.out.println(card.getName());
        }
    }

    public ArrayList<Card> getBoardSelectedCards() {
        return boardSelectedCards;
    }

    public Card getHandSelectedCard() {
        return handSelectedCard;
    }

    public void setHandSelectedCard(Card handSelectedCard) {
        this.handSelectedCard = handSelectedCard;
    }

    public void setPlayerValueOfPlay(int playerValueOfPlay) {
        this.playerValueOfPlay = playerValueOfPlay;
    }

    public int getPlayerValueOfPlay() {
        return playerValueOfPlay;
    }

    public void setPlayerValueOfBoard(int playerValueOfBoard) {
        this.playerValueOfBoard = playerValueOfBoard;
    }

    public int getPlayerValueOfBoard() {
        return playerValueOfBoard;
    }

    public Deck getDeck() {
        return deck;
    }

    public Player getHumanPlayer(){
        return humanPlayer;
    }

    public AIPlayer getAIPlayer(){
        return aIPlayer;
    }

    public ArrayList<Card> getBoardCards(){
        return boardCards;
    }

    public BoardGUI getBoardGUI() {
        return boardGUI;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public int getHandNumber(){
        return handNumber;
    }

    public void incrementHandNumber(){
        handNumber++;
    }

    public void newHand(){
        //TODO:
        boardSelectedCards.clear();
        playerValueOfBoard = 0;
        playerValueOfPlay = 0;
        handSelectedCard = null;

    }

    public void checkAndAdvanceGame() {
        System.out.println("Checking...");
        if (handNumber > 3 && roundNumber <= 5) {
            System.out.println("here");
            endHand();
        }

        if (roundNumber > 5) {
            endGame();
        }
    }
    
    private void endHand() {
        System.out.println("Hand complete! Round " + (roundNumber + 1));
        handNumber = 1;
        roundNumber++;

        if (roundNumber <= 5) {
            humanPlayer.setHand(new ArrayList<>(Arrays.asList(deck.dealCards())));
            aIPlayer.setHand(new ArrayList<>(Arrays.asList(deck.dealCards())));

            for (Card card : humanPlayer.getHand()) {
                boardGUI.addHandCard(card);
            }
            boardGUI.refreshDisplay();
        }
    }
    
    private void endGame() {
        System.out.println("Game Over! Game class");
        System.out.println("Calculating score...");
        calculateScore(humanPlayer);
        calculateScore(aIPlayer);
        // Show final scores
        // Disable buttons
        boardGUI.showGameOver();
    }

    private void calculateScore(Player player) {
        int tempCoinsCounter = 0;
        ArrayList<Card> tempCoins = new ArrayList<>();
        ArrayList<Card> tempSwords = new ArrayList<>();
        ArrayList<Card> tempCups = new ArrayList<>();
        ArrayList<Card> tempClubs = new ArrayList<>();

        for(Card card:player.getTakenCards()){
            System.out.println(card.getName() + " " + card.getValue() + " | " + player.getName());
            switch(card.getSuit()){
                case "coins":
                    player.setNumberOfCoins(player.getNumberOfCoins() + 1);
                    tempCoins.add(card);
                    if(card.getValue() == 7) {
                        player.setTotalPoints(player.getTotalPoints() + 1);
                    }
                    break;
                case "swords":
                    tempSwords.add(card);
                    break;
                case "cups":
                    tempCups.add(card);
                    break;
                case "clubs":
                    tempClubs.add(card);
                    break;
            }
        }
        
        tempCoins.sort(Comparator.comparingInt(Card::getPrimeraValue).reversed());
        tempSwords.sort(Comparator.comparingInt(Card::getPrimeraValue).reversed());
        tempCups.sort(Comparator.comparingInt(Card::getPrimeraValue).reversed());
        tempClubs.sort(Comparator.comparingInt(Card::getPrimeraValue).reversed());

        //player.setPrimeraScore(tempCoins.getFirst().getPrimeraValue() + tempSwords.getFirst().getPrimeraValue() + tempCups.getFirst().getPrimeraValue() + tempClubs.getFirst().getPrimeraValue());

        player.setNumberOfCoins(tempCoinsCounter);
    }
}