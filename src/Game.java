import java.util.*;

public class Game {
    Deck deck;
    int roundNumber;
    ArrayList<Card> initialHand;
    int initialRoundScore;
    int handNumber;
    Player humanPlayer;
    AIPlayer aIPlayer;
    ArrayList<Card> boardCards;
    private ArrayList<Card> boardSelectedCards;
    private Card handSelectedCard;
    private int playerValueOfPlay;
    private int playerValueOfBoard;
    private Player lastPlayerToCapture;
    private String difficulty;
    BoardGUI boardGUI;

    public Game() {
        newGame();
    }

    public void newGame() {
        difficulty = "hard"; // TODO: this needs to be made available to the user to change
        deck = new Deck(difficulty);
        roundNumber = 1;
        initialHand = new ArrayList<>();
        initialRoundScore = 0;
        handNumber = 1;
        humanPlayer = new Player("Human", initialHand, initialRoundScore);
        aIPlayer = new AIPlayer("AI", new ArrayList<>(), initialRoundScore);
        boardCards = new ArrayList<Card>();
        boardSelectedCards = new ArrayList<Card>();
        handSelectedCard = null;
        playerValueOfPlay = 0;
        playerValueOfBoard = 0;
        lastPlayerToCapture = null;

        boardGUI = new BoardGUI(this);
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

    public void newRound(){
        deck = new Deck(difficulty);
        roundNumber = 1;
        initialHand = new ArrayList<>();
        initialRoundScore = 0;
        handNumber = 1;
        humanPlayer = new Player("Human", initialHand, initialRoundScore); // these need to be the same player object
        aIPlayer = new AIPlayer("AI", new ArrayList<>(), initialRoundScore);
        boardCards = new ArrayList<Card>();
        boardSelectedCards = new ArrayList<Card>();
        handSelectedCard = null;
        playerValueOfPlay = 0;
        playerValueOfBoard = 0;
        lastPlayerToCapture = null;
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
        boardSelectedCards.clear();
        playerValueOfBoard = 0;
        playerValueOfPlay = 0;
        handSelectedCard = null;
    }

    public void checkAndAdvanceGame() {
        if (handNumber > 3 && roundNumber <= 6) {
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

    private void endRound(){
    }
    
    private void endGame() {
        System.out.println("Game Over! Game class");
        System.out.println("Calculating score...");
        calculateScore(humanPlayer);
        calculateScore(aIPlayer);
        compareScores(humanPlayer, aIPlayer);
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
                            ", this player " + (player.hasSevenOfCoins ? "has the perfect card" : "does not have the perfect card") );
    }

    private void compareScores(Player humanPlayer, AIPlayer aIPlayer){
        if(humanPlayer.hasSevenOfCoins){
            humanPlayer.setTotalPoints(humanPlayer.getTotalPoints()+1);
            System.out.println("Human player has seven of coins");
        } else {
            aIPlayer.setTotalPoints(aIPlayer.getTotalPoints()+1);
            System.out.println("AI player has seven of coins");
        }

        if(humanPlayer.getPrimeraScore() > aIPlayer.getPrimeraScore()){
            humanPlayer.setTotalPoints(humanPlayer.getTotalPoints()+1);
            System.out.println("Human player has primera");
        } else if(aIPlayer.getPrimeraScore() > humanPlayer.getPrimeraScore()) {
            aIPlayer.setTotalPoints(aIPlayer.getTotalPoints()+1);
            System.out.println("AI player has primera");
        }

        if(humanPlayer.getNumberOfCards() > aIPlayer.getNumberOfCards()){
            humanPlayer.setTotalPoints(humanPlayer.getTotalPoints()+1);
            System.out.println("Human player has more cards");
        } else if(aIPlayer.getNumberOfCards() > humanPlayer.getNumberOfCards()){
            aIPlayer.setTotalPoints(aIPlayer.getTotalPoints()+1);
            System.out.println("AI player has more cards");
        }

        if(humanPlayer.getNumberOfCoins() > aIPlayer.getNumberOfCoins()){
            humanPlayer.setTotalPoints(humanPlayer.getTotalPoints()+1);
            System.out.println("Human player has more coins");
        } else if(aIPlayer.getNumberOfCoins() > aIPlayer.getNumberOfCoins()){
            aIPlayer.setTotalPoints(aIPlayer.getTotalPoints()+1);
            System.out.println("AI player has more coins");
        }

        System.out.println("Total points for human player: " + humanPlayer.getTotalPoints() + " and AI player: " + aIPlayer.getTotalPoints());

        if(humanPlayer.getTotalPoints() > aIPlayer.getTotalPoints()){
            System.out.println("Human player wins");
        } else if(aIPlayer.getTotalPoints() > humanPlayer.getTotalPoints()) {
            System.out.println("AI Player wins");
        } else {
            System.out.println("Draw");
        }
    }
}