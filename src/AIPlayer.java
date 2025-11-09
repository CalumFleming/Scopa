import java.util.ArrayList;
import java.util.Comparator;

public class AIPlayer extends Player {
    public AIPlayer(String name, ArrayList<Card> hand, int roundPoints) {
        super(name, hand, roundPoints);
    }

    public void play(ArrayList<Card> aIPlayerHand, ArrayList<Card> boardCards, BoardGUI board){
        boolean match;
        int highestWeight = 0;
        System.out.println("AI Player playing...");
        hand.sort(Comparator.comparingInt(Card::getValue));// Sort the hand array from lowest value to highest value
        Card currentHandCard;
        HighestWeightCards highestWeightCards = new HighestWeightCards();

        for(int i = 0; i < aIPlayerHand.size(); i++){
            currentHandCard = aIPlayerHand.get(i);

            for(int j = 0; j < Math.pow(2, (boardCards.size())) ; j++){ // is this how many combinations?
                //get single cards
                for(int k = 0; k < boardCards.size(); k++){
                    if(boardCards.get(k) == currentHandCard ){
                        highestWeightCards.addCard(boardCards.get(k));
                        highestWeightCards.setTotalWeight(boardCards.get(k).getWeight());
                    }
                }

                // Look through and find the weight of all single cards
                // Then look through and find combinations of this card and the other cards in combinations of 2
                // Then the next combination of 3
                // Then continue until the length of the amount of cards on the board
                // Finding no cards will be last and then the ai should put the lowest card down

                if(aIPlayerHand.get(i).value == boardCards.get(i).getValue()){
                    if(boardCards.get(i).getWeight() > highestWeight){

                    }
                }
            }
        }

        for(int i = 0; i < aIPlayerHand.size(); i++) {// See if any card combinations match or if one is the 7 of coins
            for(int j = 0; j < boardCards.size(); j++){
                if(aIPlayerHand.get(i).value == boardCards.get(j).value){
                    System.out.println(aIPlayerHand.get(i).value);
                    takenCards.add(aIPlayerHand.get(i));
                    takenCards.add(boardCards.get(j));
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
    }
}
