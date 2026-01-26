import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AIVsAISimulator {

    public static void main(String[] args) {
        int aiOneWins = 0;
        int aiTwoWins = 0;
        int draws = 0;
        int numberOfGames = 10000;

        for(int i = 0; i < numberOfGames; i++){
            int winner = simulateSingleGame(); // 1 = AI One, 2 = AI Two, 0 = draw
            if (winner == 1) {
                aiOneWins++;
            } else if (winner == 2) {
                aiTwoWins++;
            } else {
                draws++;
            }
        }

        System.out.println("Games played: " + numberOfGames);
        System.out.println("AI One wins:  " + aiOneWins);
        System.out.println("AI Two wins:  " + aiTwoWins);
        System.out.println("Draws:        " + draws);
    }

    private static int simulateSingleGame() {
        Deck deck = new Deck("easy");
        deck.shuffleDeck();

        AIPlayer aiOne = new AIPlayer("AI One", new ArrayList<>(), 0, "medium");
        AIPlayer aiTwo = new AIPlayer("AI Two", new ArrayList<>(), 0, "hard");

        ArrayList<Card> board = new ArrayList<>();
        board.addAll(Arrays.asList(deck.dealBoardCards()));

        AIPlayer lastCapturer = null;

        // ... existing code ...

        for (int deal = 1; deal <= 6; deal++) {
            aiOne.setHand(new ArrayList<>(Arrays.asList(deck.dealCards())));
            aiTwo.setHand(new ArrayList<>(Arrays.asList(deck.dealCards())));

            for (int trick = 1; trick <= 3; trick++) {
                lastCapturer = doMove(aiOne, board, lastCapturer, "Deal " + deal + " Trick " + trick + " (AI_1)");
                lastCapturer = doMove(aiTwo, board, lastCapturer, "Deal " + deal + " Trick " + trick + " (AI_2)");
            }
        }

        if (!board.isEmpty() && lastCapturer != null) {
            lastCapturer.takenCards.addAll(board);
            board.clear();
        }

        int aiOnePoints = calculateGamePoints(aiOne, aiTwo);
        int aiTwoPoints = calculateGamePoints(aiTwo, aiOne);

        if (aiOnePoints > aiTwoPoints) return 1;
        if (aiTwoPoints > aiOnePoints) return 2;
        return 0;
    }

    private static int calculateGamePoints(AIPlayer aiOne, AIPlayer aiTwo) {
        aiOne.calculatePrimeraScore();
        aiOne.calculateNumberOfCards();
        aiOne.calculateNumberOfCoins();
        aiOne.calculateHasSevenOfCoins();

        aiTwo.calculatePrimeraScore();
        aiTwo.calculateNumberOfCards();
        aiTwo.calculateNumberOfCoins();
        aiTwo.calculateHasSevenOfCoins();

        int points = 0;

        if (aiOne.hasSevenOfCoins && !aiTwo.hasSevenOfCoins) {
            points++;
        }

        if (aiOne.getPrimeraScore() > aiTwo.getPrimeraScore()) {
            points++;
        }

        if (aiOne.getNumberOfCards() > aiTwo.getNumberOfCards()) {
            points++;
        }

        if (aiOne.getNumberOfCoins() > aiTwo.getNumberOfCoins()) {
            points++;
        }

        points += aiOne.getScopas();

        return points;
    }

    private static AIPlayer doMove(AIPlayer player, ArrayList<Card> board, AIPlayer lastCapturer, String label) {
        int takenBefore = player.takenCards.size();
        int boardBefore = board.size();

        // ... existing code ...

        player.play(player.getHand(), board, null); // passing null means it will play headless

        int takenAfter = player.takenCards.size();
        int boardAfter = board.size();

        boolean captured = takenAfter > takenBefore || boardAfter < boardBefore;
        if (captured) {
            lastCapturer = player;
        }

        // ... existing code ...
        return lastCapturer;
    }

    private static void printScore(Player p) {
        p.calculatePrimeraScore();
        p.calculateNumberOfCards();
        p.calculateNumberOfCoins();
        p.calculateHasSevenOfCoins();

        System.out.println(
                p.getName()
                        + " | cards=" + p.getNumberOfCards()
                        + " coins=" + p.getNumberOfCoins()
                        + " primera=" + p.getPrimeraScore()
                        + " scopas=" + p.getScopas()
                        + " has7Coins=" + p.hasSevenOfCoins
        );
    }

    private static String printCardNames(List<Card> cards) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < cards.size(); i++) {
            Card c = cards.get(i);
            sb.append(c.getName());
            if (i < cards.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}