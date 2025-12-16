import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton newGameButton;
    private JLabel humanNumberOfScopasLabel;
    private JLabel humanNumberOfScopasDisplay;
    private JLabel aiNumberOfScopas;
    private JLabel aiNumberOfScopasDisplay;

    public RoundEndCard(Game game){
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


        newGameButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                game.newGame();

                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                frame.setContentPane(game.getBoardGUI().getPanel());
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
