import java.util.Arrays;
import java.util.Comparator;

public class AIPlayer extends Player {
    public AIPlayer(String name, Card[] hand, int roundPoints) {
        super(name, hand, roundPoints);
    }

    public void play(Card[] aIPlayerHand, Card[] boardCards){
        System.out.println("AI Player playing...");
        Arrays.sort(hand, Comparator.comparingInt(Card::getValue));// Sort the hand array from lowest vlaue to highest value
        for(int i = 0; i < aIPlayerHand.length; i++) {// See if any card combinations match or if one is the 7 of coins
            for(int j = 0; j < boardCards.length; j++){
                if(aIPlayerHand[i].value == boardCards[j].value){
                    System.out.println(aIPlayerHand[i].name);
                } else {
                }
            }
        }
        System.out.println("AI Player played");
        // Select them
        // If not, place the lowest value card
    }
}
