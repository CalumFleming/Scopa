import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    Scanner s = new Scanner(System.in);
    Deck deck=new Deck();
    int roundNumber = 1;
    Card[] initialHand = new Card[3];
    int initialRoundScore = 0;
    Player humanPlayer = new Player("Human", initialHand, initialRoundScore);
    Player aIPlayer = new Player("AI", initialHand, initialRoundScore);
    Card[] boardCards = new Card[40]; // this doesn't need to be 40, change this to an arraylist later
    ArrayList<Card> playerTakenCards = new ArrayList<Card>();

    int handCardSelected;
    int boardCardSelected;

    public void newGame(){
        deck.shuffleDeck();

        humanPlayer.setHand(deck.dealCards());
        aIPlayer.setHand(deck.dealCards());
        boardCards = deck.dealBoardCards();

        this.newRound(roundNumber);

        int handLoopValid = 0;
        int boardLoopValid = 0;


        while(handLoopValid==0){
            System.out.println("Select a card ID from your hand: ");
            try{
                this.handCardSelected= s.nextInt();
                s.nextLine();
                handLoopValid++;
            } catch(Exception e){
                System.out.println("Invalid Hand Input");
                s.nextLine();
                break;
            }
        }

        while(boardLoopValid==0){
            System.out.println("Select a card ID from the board: ");
            try{
                this.boardCardSelected = s.nextInt();
                s.nextLine();
                boardLoopValid++;
            } catch(Exception e){
                System.out.println("Invalid Board Input");
                break;
            }
        }

        if(deck.getCardFromId(handCardSelected).getValue()==deck.getCardFromId(boardCardSelected).getValue()){
            System.out.println("Match");
        } else {
            System.out.println("Mismatch");
        }

    }

    public void newRound(int roundNumber){
        System.out.println("Round number: " + roundNumber + "\n");
        System.out.println("Board Cards: \n" + deck.stringRepresentCards(boardCards));
        System.out.println("Your Cards: \n" + deck.stringRepresentCards(humanPlayer.getHand()));

        roundNumber++;
        this.roundNumber = roundNumber;

    }


}
