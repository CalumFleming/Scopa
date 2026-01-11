import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverScreen {
    private JPanel mainPanel;
    private JPanel ScreenPanel;
    private JButton newGameButton;
    private JLabel humanPlayerLabel;
    private JLabel humanPlayerScore;
    private JLabel aIPlayerLabel;
    private JLabel aIPlayerScore;

    public GameOverScreen(Game game){
        humanPlayerScore.setText(String.valueOf(game.getHumanPlayer().getTotalPoints()));
        aIPlayerScore.setText(String.valueOf(game.getAIPlayer().getTotalPoints()));

        newGameButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);

                MenuScreen menuScreen = new MenuScreen();
                frame.setContentPane(menuScreen.getPanel());

                frame.revalidate();
                frame.repaint();
            }
        });

    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

}
