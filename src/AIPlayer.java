import java.util.ArrayList;
import java.util.Comparator;

public class AIPlayer extends Player {
    public AIPlayer(String name, ArrayList<Card> hand, int roundPoints) {
        super(name, hand, roundPoints);
    }

    public void play(ArrayList<Card> aIPlayerHand, ArrayList<Card> boardCards, BoardGUI board){
        System.out.println("AI Player playing...");
        hand.sort(Comparator.comparingInt(Card::getValue));// Sort the hand array from lowest value to highest value
        for(int i = 0; i < aIPlayerHand.size(); i++) {// See if any card combinations match or if one is the 7 of coins
            for(int j = 0; j < boardCards.size(); j++){
                if(aIPlayerHand.get(i).value == boardCards.get(j).value){
                    System.out.println(aIPlayerHand.get(i).value);
                    board.removeBoardCard(boardCards.get(j));
                    hand.remove(aIPlayerHand.get(i));
                    System.out.println("Match");
                    return;
                } else {
                }
            }
        }
        board.addBoardCard(hand.getFirst());
        boardCards.add(hand.getFirst());
        hand.removeFirst();
        board.refreshDisplay();
        System.out.println("AI Player played");
        // Select them
        // If not, place the lowest value card
    }
}
