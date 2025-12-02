import javax.swing.*;

public class RoundEndCard {
    private JPanel mainPanel;
    private JLabel roundOverLabel;
    private JLabel humanScore;
    private JLabel aIScore;
    private JLabel aIHasPrimeaLabel;
    private JLabel aIHasPrimeraDisplay;
    private JLabel humanHasPrimeraLabel;
    private JLabel humanHasPrimeraDisplay;
    private JLabel humanHasSevenOfCoinsLabel;
    private JLabel humanHasSevenOfCoinsDisplay;
    private JLabel aIHasSevenOfCoinsLabel;
    private JLabel aIHasSevenOfCoinsDisplay;
    private JLabel humanHasMostCardsLabel;
    private JLabel humanHasMostCardsDisplay;
    private JLabel aIHasMostCardsLabel;
    private JLabel aIHasMostCardsDisplay;
    private JLabel humanHasMostCoinsLabel;
    private JLabel humanHasMostCoinsDisplay;
    private JLabel aIHasMostCoinsLabel;
    private JLabel aIHasMostCoinsDisplay;

    public RoundEndCard() {
        // Constructor
    }

    public void displayScores(Player humanPlayer, AIPlayer aIPlayer) {
        // Set main title
        roundOverLabel.setText("Game Over - Final Scores");
        
        // Display total scores
        humanScore.setText("Human Score: " + humanPlayer.getTotalPoints());
        aIScore.setText("AI Score: " + aIPlayer.getTotalPoints());
        
        // Seven of Coins
        if (humanPlayer.hasSevenOfCoins) {
            humanHasSevenOfCoinsDisplay.setText("✓ (1 point)");
        } else {
            humanHasSevenOfCoinsDisplay.setText("✗");
        }
        
        if (aIPlayer.hasSevenOfCoins) {
            aIHasSevenOfCoinsDisplay.setText("✓ (1 point)");
        } else {
            aIHasSevenOfCoinsDisplay.setText("✗");
        }
        
        // Primera
        if (humanPlayer.getPrimeraScore() > aIPlayer.getPrimeraScore()) {
            humanHasPrimeraDisplay.setText("✓ (1 point) - Score: " + humanPlayer.getPrimeraScore());
            aIHasPrimeraDisplay.setText("✗ - Score: " + aIPlayer.getPrimeraScore());
        } else {
            humanHasPrimeraDisplay.setText("✗ - Score: " + humanPlayer.getPrimeraScore());
            aIHasPrimeraDisplay.setText("✓ (1 point) - Score: " + aIPlayer.getPrimeraScore());
        }
        
        // Most Coins
        if (humanPlayer.getNumberOfCoins() > aIPlayer.getNumberOfCoins()) {
            humanHasMostCoinsDisplay.setText("✓ (1 point) - " + humanPlayer.getNumberOfCoins() + " coins");
            aIHasMostCoinsDisplay.setText("✗ - " + aIPlayer.getNumberOfCoins() + " coins");
        } else {
            humanHasMostCoinsDisplay.setText("✗ - " + humanPlayer.getNumberOfCoins() + " coins");
            aIHasMostCoinsDisplay.setText("✓ (1 point) - " + aIPlayer.getNumberOfCoins() + " coins");
        }
        
        // Most Cards
        if (humanPlayer.getNumberOfCards() > aIPlayer.getNumberOfCards()) {
            humanHasMostCardsDisplay.setText("✓ (1 point) - " + humanPlayer.getNumberOfCards() + " cards");
            aIHasMostCardsDisplay.setText("✗ - " + aIPlayer.getNumberOfCards() + " cards");
        } else {
            humanHasMostCardsDisplay.setText("✗ - " + humanPlayer.getNumberOfCards() + " cards");
            aIHasMostCardsDisplay.setText("✓ (1 point) - " + aIPlayer.getNumberOfCards() + " cards");
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
