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

//        while(roundNumber <= 6) { // This will be the game loop
//            while(handNumber<=3){ // Move the game logic that's in the GUI to here where the function to handle a hand is called
//                // TODO: game.doSomethingForTheNextHand() <- move game logic to this function
//            }
//            handNumber=1;
//            humanPlayer.setHand(deck.dealCards());
//            aIPlayer.setHand(deck.dealCards());
//            roundNumber++;
//        }

        System.out.println("End of game.");
    }

    public ArrayList<Card> getBoardSelectedCards() {
        return boardSelectedCards;
    }

//    public void setBoardSelectedCards(ArrayList<Card> boardSelectedCards) {
//        this.boardSelectedCards = boardSelectedCards; // this is wrong it needs to add the card, this is trying to set an arraylist by passing it but it needs added
//    }

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
}