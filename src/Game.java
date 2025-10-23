import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    Deck deck = new Deck();
    int roundNumber = 1;
    ArrayList<Card> initialHand = new ArrayList<>();
    int initialRoundScore = 0;
    int handNumber = 1;
    Player humanPlayer = new Player("Human", initialHand, initialRoundScore);
    AIPlayer aIPlayer = new AIPlayer("AI", new ArrayList<>(), initialRoundScore);
    ArrayList<Card> boardCards = new ArrayList<Card>();
    ArrayList<Card> playerTakenCards = new ArrayList<Card>();
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

        this.newRound(roundNumber);

        for (int i = 0; i < humanPlayer.getHand().size(); i++) {
            boardGUI.addHandCard(humanPlayer.getHand().get(i));
        }
        for (int i = 0; i < boardCards.size(); i++) {
            boardGUI.addBoardCard(boardCards.get(i));
        }

        while(roundNumber <= 6) { // Dont need this
            while(handNumber<=3){ // Move the game logic that's in the GUI to here where the function to handle a hand is called
                System.out.println("hand"+handNumber);
                return;
                // TODO: game.doSomethingForTheNextHand() <- move game logic to this function
            }
            handNumber=1;
            Card[] tempPlayerHand = deck.dealCards();
            Card[] tempAIHand = deck.dealCards();
            for(int i = 0; i<tempPlayerHand.length; i++){ // I know this sucks
                humanPlayer.getHand().add(tempPlayerHand[i]);
                aIPlayer.getHand().add(tempAIHand[i]);
            }
            roundNumber++;
        }

        System.out.println("End of game.");
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

    public ArrayList<Card> getPlayerTakenCards(){
        return playerTakenCards;
    }

    public Deck getDeck() {
        return deck;
    }

    public AIPlayer getAIPlayer(){
        return aIPlayer;
    }

    public ArrayList<Card> getBoardCards(){
        return boardCards;
    }

    public void newRound(int roundNumber) {
//        System.out.println("Round number: " + roundNumber + "\n");
//        System.out.println("Board Cards: \n" + deck.stringRepresentCards(boardCards));
//        System.out.println("Your Cards: \n" + deck.stringRepresentCards(humanPlayer.getHand()));

        roundNumber++;
        this.roundNumber = roundNumber;

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

    public void validPlay(){
        playerTakenCards.add(handSelectedCard); // Needs a get card method
    }

    public void cantPickUp(){

    }

    public void checkAndAdvanceGame() {
        System.out.println("Checking...");
        if (handNumber > 3 && roundNumber <= 6) {
            System.out.println("here");
            endHand();
        }

        if (roundNumber > 6) {
            endGame();
        }
    }
    
    private void endHand() {
        System.out.println("Hand complete!");
        handNumber = 1;

        if (roundNumber <= 6) {
            humanPlayer.setHand(new ArrayList<>(Arrays.asList(deck.dealCards())));
            aIPlayer.setHand(new ArrayList<>(Arrays.asList(deck.dealCards())));

            for (Card card : humanPlayer.getHand()) {
                boardGUI.addHandCard(card);
            }
            boardGUI.refreshDisplay();
        }
    }
    
    private void endRound() {
        System.out.println("Round " + roundNumber + " complete!");
        // Calculate round points
        // Update scores
        roundNumber++;
        
        if (roundNumber <= 6) {
            newRound(roundNumber);
        }
        boardGUI.refreshDisplay();
    }
    
    private void endGame() {
        System.out.println("Game Over!");
        // Show final scores
        // Disable buttons
        boardGUI.showGameOver();
    }
}