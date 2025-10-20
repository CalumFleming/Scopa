import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    Deck deck = new Deck();
    int roundNumber = 1;
    Card[] initialHand = new Card[3];
    int initialRoundScore = 0;
    Player humanPlayer = new Player("Human", initialHand, initialRoundScore);
    AIPlayer aIPlayer = new AIPlayer("AI", initialHand, initialRoundScore);
    ArrayList<Card> boardCards = new ArrayList<Card>(); // this doesn't need to be 40, change this to an arraylist later
    ArrayList<Card> playerTakenCards = new ArrayList<Card>();
    private ArrayList<Card> boardSelectedCards = new ArrayList<Card>();
    private Card handSelectedCard;
    private int playerValueOfPlay = 0;
    private int playerValueOfBoard = 0;

    BoardGUI boardGUI = new BoardGUI(this);

    public void newGame() {
        deck.shuffleDeck();

        humanPlayer.setHand(deck.dealCards());
        aIPlayer.setHand(deck.dealCards());
        Card[] tempHand = deck.dealBoardCards(); // This sucks but will change later
        for(Card card:tempHand){
            boardCards.add(card);
        }


        this.newRound(roundNumber);

        for (int i = 0; i < humanPlayer.getHand().length; i++) {
            boardGUI.addHandCard(humanPlayer.getHand()[i]);
        }
        for (int i = 0; i < boardCards.size(); i++) {
            boardGUI.addBoardCard(boardCards.get(i));
        }

        while(roundNumber <= 6) {
            roundNumber++;
        }
    }

    public ArrayList<Card> getBoardSelectedCards() {
        return boardSelectedCards;
    }

    public void setBoardSelectedCards(ArrayList<Card> boardSelectedCards) {
        this.boardSelectedCards = boardSelectedCards; // this is wrong it needs to add the card, this is trying to set an arraylist by passing it but it needs added
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
}