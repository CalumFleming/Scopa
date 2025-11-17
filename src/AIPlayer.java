import java.util.ArrayList;
import java.util.Comparator;

public class AIPlayer extends Player {
    public AIPlayer(String name, ArrayList<Card> hand, int roundPoints) {
        super(name, hand, roundPoints);
    }

    public void play(ArrayList<Card> aIPlayerHand, ArrayList<Card> boardCards, BoardGUI board){
        boolean match;
        int highestWeight = 0; // Remove this when removing the old algorythm
        System.out.println("AI Player playing...");
        hand.sort(Comparator.comparingInt(Card::getValue));// Sort the hand array from lowest value to highest value
        Card currentHandCard;
        HighestWeightCards highestWeightCards = new HighestWeightCards();

        System.out.println("Current Hand Cards:");
        for(Card card : aIPlayerHand){
            System.out.print(card.getName() + " ");
        }
        System.out.println("/n board cards");
        for(Card card : boardCards){
            System.out.print(card.getName() + " ");
        }

        // NEW IMPLEMENTATION OF AI
        HighestWeightCards bestCombination = findBestMove(boardCards, aIPlayerHand);

        if(bestCombination.handCard != null && !bestCombination.getCards().isEmpty()){
            String printCards = "";
            for(Card card : bestCombination.getCards()){
                printCards += card.getName() + " | ";
            }
            System.out.println("AI captures cards with toal weight:" + bestCombination.getTotalWeight() + "  " + printCards + " using the card " + bestCombination.handCard.getName());
            for(Card boardCard : bestCombination.getCards()){
                takenCards.add(boardCard);
                board.removeBoardCard(boardCard);
            }
            hand.remove(bestCombination.handCard);
            System.out.println("Match found, and card taken");
        } else {
            System.out.println("No cards found to take, playing the lowest card");
            board.addBoardCard(hand.getFirst());
            boardCards.add(hand.getFirst());
            hand.removeFirst();
        }
        board.refreshDisplay();
        System.out.println("AI Player played");

        System.out.println("Current Hand Cards:");
        for(Card card : aIPlayerHand){
            System.out.print(card.getName() + " ");
        }
        System.out.println("/n board cards");
        for(Card card : boardCards){
            System.out.print(card.getName() + " ");
        }
    }

    public HighestWeightCards findBestMove(ArrayList<Card> boardCards, ArrayList<Card> aIPlayerHand){
        HighestWeightCards bestCombination = new HighestWeightCards();

        for(Card handCard : aIPlayerHand){
            ArrayList<Card> currentCombination = new ArrayList<>();
            ArrayList<Card> bestCombinationForThisCard = new ArrayList<>();
            int bestWeightForThisCard = 0;

            findCombinations(boardCards, 0, currentCombination, handCard.getValue(), bestCombinationForThisCard, bestWeightForThisCard, handCard);

            if(!bestCombinationForThisCard.isEmpty()){
                int combinedWeight = HighestWeightCards.getCardsWeight(bestCombinationForThisCard);
                if(combinedWeight > bestCombination.getTotalWeight()){
                    bestCombination.setHandCard(handCard);
                    bestCombination.setTotalWeight(combinedWeight);
                    bestCombination.getCards().clear();
                    bestCombination.getCards().addAll(bestCombinationForThisCard);
                }
            }
        }

        return bestCombination;
    }

    public void findCombinations(ArrayList<Card> boardCards, int startIndex, ArrayList<Card> currentCombination, int targetValue, ArrayList<Card> bestCombination, int bestWeight, Card handCard){
        int currentValue = 0;
        for(Card card : currentCombination){
            currentValue += card.getValue();
        }

        if(currentValue == targetValue && !currentCombination.isEmpty()){
            int currentWeight = HighestWeightCards.getCardsWeight(currentCombination);

            if(currentWeight > bestWeight || bestCombination.isEmpty()){
                bestCombination.clear();
                bestCombination.addAll(currentCombination);
            }
            return;
        }

        if(currentValue > targetValue){
            return;
        }

        for(int i = startIndex; i < boardCards.size(); i++){
            Card card = boardCards.get(i);
            currentCombination.add(card);
            //this is the recursive backtracking part
            findCombinations(boardCards, i+1, currentCombination, targetValue, bestCombination, bestWeight, handCard);
            currentCombination.remove(card);
        }
    }
}
