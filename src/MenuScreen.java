import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuScreen {
    private JButton easyButton;
    private JPanel panel1;
    private JButton mediumButton;
    private JButton hardButton;

    public MenuScreen(){
        easyButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                startGame("easy");
            }
        });
        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame("medium");
            }
        });
        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame("hard");
            }
        });
    }

    private void startGame(String difficulty) {
        Game game = new Game(difficulty);
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
        frame.setContentPane(game.getBoardGUI().getPanel());
        frame.revalidate();
        frame.repaint();
    }

    public JPanel getPanel(){
        return panel1;
    }
}
