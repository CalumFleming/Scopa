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
    private int playerValueOfPlay = 0;
    private int playerValueOfBoard = 0;
    private Player lastPlayerToCapture = null;

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

    public void setLastPlayerToCapture(Player player) {
        this.lastPlayerToCapture = player;
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
        if (handNumber > 3 && roundNumber <= 6) {
            System.out.println("here");
            endHand();
        }

        if (roundNumber > 6) {
            // Award remaining board cards to last player who made a capture
            if (!boardCards.isEmpty() && lastPlayerToCapture != null) {
                System.out.println("Awarding " + boardCards.size() + " remaining cards to " + lastPlayerToCapture.getName());
                lastPlayerToCapture.takenCards.addAll(boardCards);
                boardCards.clear();
            }
            endGame();
        }
    }
    
    private void endHand() {
        System.out.println("Hand complete! Round " + (roundNumber + 1));
        handNumber = 1;
        roundNumber++;

        if (roundNumber <= 6) {
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
        player.calculatePrimeraScore();
        player.calculateNumberOfCards();
        player.calculateNumberOfCoins();
        player.calculateHasSevenOfCoins();

        System.out.println("Primera score for " + player.getName() + ": " + player.getPrimeraScore() +
                            ", number of cards: " + player.getNumberOfCards() +
                            ", number of coins: " + player.getNumberOfCoins() +
                            ", this player " + (player.hasSevenOfCoins ? "yes" : "no") );
    }
}