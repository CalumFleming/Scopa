import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AIVsAISimulator {

    public static void main(String[] args) {
        String[] difficulties = {"easy", "medium", "hard"};

        for (String difficulty : difficulties) {
            System.out.println("AI vs AI Simulation - Difficulty: " + difficulty);
            simulateSingleGame(difficulty);
        }
    }

    private static void simulateSingleGame(String difficulty) {
        Deck deck = new Deck(difficulty);
        deck.shuffleDeck();

        AIPlayer aiOne = new AIPlayer("AI One", new ArrayList<>(), 0);
        AIPlayer aiTwo = new AIPlayer("AI Two", new ArrayList<>(), 0);

        ArrayList<Card> board = new ArrayList<>();
        board.addAll(Arrays.asList(deck.dealBoardCards()));

        AIPlayer lastCapturer = null;

        System.out.println("Initial board: " + printCardNames(board));

        for (int deal = 1; deal <= 6; deal++) {
            aiOne.setHand(new ArrayList<>(Arrays.asList(deck.dealCards())));
            aiTwo.setHand(new ArrayList<>(Arrays.asList(deck.dealCards())));

            System.out.println("\n--- Deal " + deal + " ---");
            System.out.println("AI_1 hand: " + printCardNames(aiOne.getHand()));
            System.out.println("AI_2 hand: " + printCardNames(aiTwo.getHand()));
            System.out.println("Board   : " + printCardNames(board));

            for (int trick = 1; trick <= 3; trick++) {
                lastCapturer = doMove(aiOne, board, lastCapturer, "Deal " + deal + " Trick " + trick + " (AI_1)");
                lastCapturer = doMove(aiTwo, board, lastCapturer, "Deal " + deal + " Trick " + trick + " (AI_2)");
            }
        }

        if (!board.isEmpty() && lastCapturer != null) {
            System.out.println("\nAwarding remaining board cards (" + board.size() + ") to " + lastCapturer.getName());
            lastCapturer.takenCards.addAll(board);
            board.clear();
        }

        System.out.println("\nScore Summary");
        printScore(aiOne);
        printScore(aiTwo);
    }

    private static AIPlayer doMove(AIPlayer player, ArrayList<Card> board, AIPlayer lastCapturer, String label) {
        int takenBefore = player.takenCards.size();
        int boardBefore = board.size();

        System.out.println("\n[" + label + "]");
        System.out.println("Board before: " + printCardNames(board));

        player.play(player.getHand(), board, null); // passing null means it will play headless

        int takenAfter = player.takenCards.size();
        int boardAfter = board.size();

        boolean captured = takenAfter > takenBefore || boardAfter < boardBefore;
        if (captured) {
            lastCapturer = player;
        }

        System.out.println("Board after : " + printCardNames(board));
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
