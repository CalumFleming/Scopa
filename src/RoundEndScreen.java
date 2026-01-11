import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoundEndScreen {
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
    private JButton newGameButton;
    private JLabel humanNumberOfScopasLabel;
    private JLabel humanNumberOfScopasDisplay;
    private JLabel aiNumberOfScopas;
    private JLabel aiNumberOfScopasDisplay;

    public RoundEndScreen(Game game){
        Player human = game.getHumanPlayer();
        Player ai = game.getAIPlayer();
        
        this.humanScore.setText("Human Score: " + human.getTotalPoints());
        this.aIScore.setText("AI Score: " + ai.getTotalPoints());
        
        this.humanHasPrimeraDisplay.setText(human.getPrimeraScore() > ai.getPrimeraScore() ? "Yes" : "No");
        this.aIHasPrimeraDisplay.setText(ai.getPrimeraScore() > human.getPrimeraScore() ? "Yes" : "No");
        
        this.humanHasSevenOfCoinsDisplay.setText(human.hasSevenOfCoins ? "Yes" : "No");
        this.aIHasSevenOfCoinsDisplay.setText(ai.hasSevenOfCoins ? "Yes" : "No");
        
        this.humanHasMostCardsDisplay.setText(human.getNumberOfCards() > ai.getNumberOfCards() ? "Yes" : "No");
        this.aIHasMostCardsDisplay.setText(ai.getNumberOfCards() > human.getNumberOfCards() ? "Yes" : "No");
        
        this.humanHasMostCoinsDisplay.setText(human.getNumberOfCoins() > ai.getNumberOfCoins() ? "Yes" : "No");
        this.aIHasMostCoinsDisplay.setText(ai.getNumberOfCoins() > human.getNumberOfCoins() ? "Yes" : "No");

        this.humanNumberOfScopasDisplay.setText(Integer.toString(human.getScopas()));
        this.aiNumberOfScopasDisplay.setText(Integer.toString(ai.getScopas()));

        if (game.isGameOver()) { //TODO: make this display who won
            roundOverLabel.setText("Game Over");
            newGameButton.setText("New Game");
        } else {
            roundOverLabel.setText("Round Over");
            newGameButton.setText("Next Round");
        }

        newGameButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){ // the reon it shows the round end card then the game over card is this. It's only being told to switch them when clicking the button on the round end card.
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);

                if (game.isGameOver()) {
                    GameOverScreen gameOverScreen = new GameOverScreen(game);
                    frame.setContentPane(gameOverScreen.getMainPanel());
                } else {
                    game.newRound();
                    frame.setContentPane(game.getBoardGUI().getPanel());
                }
                
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
