import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Deck {
    Card[] deck = new Card[40];
    private HashMap<Integer, Card> iDAndCard = new HashMap<Integer, Card>();
    private int nextCardIndex = 0;
    String difficulty;

    public Deck(String difficulty) { // TODO: This should accept a difficulty so it can be used to generate the card weights
        this.difficulty = difficulty;
        String[] numbers = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Soldier", "Horse", "King"};
        String[] suits = {"Coins", "Swords", "Cups", "Clubs"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] primeraValues = {16, 12, 13, 14, 15, 18, 21, 10, 10, 10};
        int cardId = 1;
        int deckIndex = 0;

        for(int i=0;i<suits.length;i++) {
            for(int j=0;j<numbers.length;j++) {
                int weight = 0;
                if(suits[i].equals("Coins")) {
                    if(values[j] == 7) { // TODO: These weights should be changed to accomidate difficulties
                        switch(difficulty){ // I think this would be the easiest way to change difficulties
                            case "Easy": // this needs to replicate how a new player would choose, currently it treats all card the same so will pick the first one it finds
                                weight = 1;
                                System.out.println("Easy");
                                break;
                            case "Medium": // Harder - it could pick a random card but currently it will strongly favour the seven of coins over any card and slightly all other coins
                                weight = 20;
                                System.out.println("Medium");
                                break;
                            case "Hard":
                                weight = 8;
                                System.out.println("Hard");
                                break;
                        }
                    } else if(values[j] == 6) {
                        switch(difficulty){
                            case "Easy":
                                weight = 1;
                                break;
                            case "Medium":
                                weight = 2;
                                break;
                            case "Hard":
                                weight = 6;
                                break;
                        }
                    } else {
                        switch(difficulty){
                            case "Easy":
                                weight = 1;
                                break;
                            case "Medium":
                                weight = 2;
                                break;
                            case "Hard":
                                weight = 3;
                                break;
                        }
                    }
                } else if(values[j] == 7) {
                    switch(difficulty){
                        case "Easy":
                            weight = 1;
                            break;
                        case "Medium":
                            weight = 1;
                            break;
                        case "Hard":
                            weight = 5;
                            break;
                    }
                } else if(values[j] == 6) {
                    switch(difficulty){
                        case "Easy":
                            weight = 1;
                            break;
                        case "Medium":
                            weight = 1;
                            break;
                        case "Hard":
                            weight = 4;
                            break;
                    }
                } else {
                    switch(difficulty){
                        case "Easy":
                            weight = 1;
                            break;
                        case "Medium":
                            weight = 1;
                            break;
                        case "Hard":
                            weight = 1;
                            break;
                    }
                }

                String tempPathString = "resources/images/" + numbers[j].toLowerCase() + "Of" + suits[i] + ".png";
                Card tempCard = new Card(cardId, numbers[j], suits[i], values[j], primeraValues[j], weight, tempPathString);
                deck[deckIndex] = tempCard;
                iDAndCard.put(cardId, tempCard);
                cardId++;
                deckIndex++;
            }
        }
    }

    public void shuffleDeck() {
        Random randomNumber = new Random();

        for(int i = 0; i < deck.length; i++) {
            int randomIndex = randomNumber.nextInt(i + 1);
            Card swapValue = deck[randomIndex];
            deck[randomIndex] = deck[i];
            deck[i] = swapValue;
        }
    } // Fisher - Yates Shuffle

    public Card getCard(int i){
        return deck[i];
    }

    public Card[] dealCards() {
        Card[] deltCards = new Card[3];
        for (int i = 0; i < 3; i++) {
            deltCards[i]= deck[nextCardIndex++];
        }
        return deltCards;
    }

    public Card[] dealBoardCards(){
        Card[] boardCards = new Card[4];
        for (int i = 0; i < 4; i++) {
            boardCards[i]= deck[nextCardIndex++];
        }
        return boardCards;
    }

    public String stringRepresentCards(ArrayList<Card> cards){
        String stringRepresentCards = "";
        for (int i = 0; i < cards.size(); i++) {
            stringRepresentCards += cards.get(i).getName() + " of " + cards.get(i).suit + " " + cards.get(i).getId() + " | ";
        }
        return stringRepresentCards;
    }

    public Card getCardFromId(int id){
        return iDAndCard.get(id);
    }

    public Card[] getDeck(){
        return deck;
    }

}
