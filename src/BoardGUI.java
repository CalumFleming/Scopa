import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class BoardGUI {
    private JPanel panel;
    private JPanel boardPanel;
    private JPanel handPanel;
    private JPanel SelectedPane;
    private JLabel handSelectedLabel;
    private JLabel boardSelectedLabel;
    private JButton handSelectedButton;
    private ArrayList<JButton> boardSelectedButtons;
    private JButton enterButton;
    private JLabel roundCounterLabel;
    private JLabel handCounterLabel;
    private Game game;
    private ArrayList<JButton> handCardButtons;
    private HashMap<Card, JButton> cardToButton = new HashMap<>(); // Make a button to card


    public BoardGUI(Game game) {
        this.game = game;
        this.handCardButtons = new ArrayList<>();
        this.boardSelectedButtons = new ArrayList<>();
        handSelectedLabel.setText("Hand Card:");
        boardSelectedLabel.setText("Board Card:");
        roundCounterLabel.setText("Round Number:" + game.getRoundNumber());
        handCounterLabel.setText("Hand Number " + game.getHandNumber());
        enterButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (boardSelectedButtons.size() == 0) { // This is when a player is putting a card down
                    Card placedCard = game.getHandSelectedCard();
                    
                    handCardButtons.remove(placedCard);
                    handPanel.remove(handSelectedButton);
                    handPanel.revalidate();
                    handPanel.repaint();
                    
                    game.getBoardCards().add(placedCard);
                    addBoardCard(placedCard);
                    
                    boardSelectedButtons.clear();
                    game.getBoardSelectedCards().clear();

                    game.setPlayerValueOfBoard(0);
                    game.setPlayerValueOfPlay(0);
                    game.setHandSelectedCard(null);
                    handSelectedButton = null;

                    handSelectedLabel.setText("Hand Card:");
                    boardSelectedLabel.setText("Board Card:");

                    System.out.println("calling AI player play()");
                    game.getAIPlayer().play(game.getAIPlayer().getHand(), game.getBoardCards(), game.getBoardGUI());
                    handCounterLabel.setText("Hand Number " + game.getHandNumber());
                    game.incrementHandNumber();
                    game.checkAndAdvanceGame();

                } else if (game.getPlayerValueOfBoard() == game.getPlayerValueOfPlay()) { // Valid
                    System.out.println("Valid play");
                    game.getPlayerTakenCards().add(game.getHandSelectedCard());
                    for (Card card : game.getBoardSelectedCards()) {
                        game.getBoardCards().remove(card);
                    }
                    handCardButtons.remove(game.getHandSelectedCard());
                    handPanel.remove(handSelectedButton);
                    handPanel.revalidate();
                    handPanel.repaint();

                    for (JButton btn : boardSelectedButtons) {
                        boardPanel.remove(btn);
                    }
                    boardPanel.revalidate();
                    boardPanel.repaint();

                    boardSelectedButtons.clear();
                    game.getBoardSelectedCards().clear();

                    game.setPlayerValueOfBoard(0);
                    game.setPlayerValueOfPlay(0);
                    game.setHandSelectedCard(null);
                    game.incrementHandNumber();
                    handSelectedButton = null;

                    handSelectedLabel.setText("Hand Card:");
                    boardSelectedLabel.setText("Board Card:");

                    System.out.println("calling AI player play()");
                    game.getAIPlayer().play(game.getAIPlayer().getHand(), game.getBoardCards(), game.getBoardGUI());
                    game.incrementHandNumber();;
                    handCounterLabel.setText("Hand Number " + game.getHandNumber());
                    game.checkAndAdvanceGame();
                } else { // Not valid
                    System.out.println("Invalid play");
                }
            }
        });
    }

    public void refreshDisplay() {
        handPanel.revalidate();
        handPanel.repaint();
        boardPanel.revalidate();
        boardPanel.repaint();
    }


    public void addHandCard(Card card) {
        JButton cardButton = new JButton();
        cardButton.setLabel(card.getName());
        handCardButtons.add(cardButton);
        cardButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.getHandSelectedCard() == card) {
                    game.setHandSelectedCard(game.getDeck().getBlankCard());
                    game.setPlayerValueOfPlay(game.getDeck().getBlankCard().getValue());
                    handSelectedLabel.setText("Hand Card:" + game.getHandSelectedCard().getValue());
                } else {
                    game.setPlayerValueOfPlay(card.getValue());
                    game.setHandSelectedCard(card);
                    handSelectedButton = cardButton;
                    handSelectedLabel.setText("Hand Card:" + card.getName() + game.getHandSelectedCard().getValue());
                }
            }
        });
        handPanel.add(cardButton);
    }

    public void addBoardCard(Card card) {
        JButton cardButton = new JButton();
        cardButton.setLabel(card.getName());
        cardToButton.put(card, cardButton);
        cardButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!game.getBoardCards().contains(card)) {
                    return;
                }
                
                boolean found = false;
                String boardSelected = "";

                for (Card c : game.getBoardSelectedCards()) {
                    if (card == c) {
                        found = true;
                        break;
                    }
                }
                
                if (found) {
                    game.getBoardSelectedCards().remove(card);
                    boardSelectedButtons.remove(cardButton);
                    game.setPlayerValueOfBoard(game.getPlayerValueOfBoard() - card.getValue());
                } else {
                    game.getBoardSelectedCards().add(card);
                    boardSelectedButtons.add(cardButton);
                    game.setPlayerValueOfBoard(game.getPlayerValueOfBoard() + card.getValue());
                }
                for (Card c : game.getBoardSelectedCards()) {
                    boardSelected += c.getName() + " | ";
                }

                if (game.getBoardSelectedCards().isEmpty()) {
                    boardSelectedLabel.setText("Board Card:");
                } else {
                    boardSelectedLabel.setText("Board Card: " + boardSelected + game.getPlayerValueOfBoard());
                }
            }
        });
        boardPanel.add(cardButton);
    }

    public void removeBoardCard(Card card) {
        boardPanel.remove(cardToButton.get(card));
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    public String getBoardCardsString(ArrayList<Card> cards) {
        String boardCardsString = "";
        for (Card card : cards) {
            boardCardsString += card.getName() + " | ";
        }
        return boardCardsString;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void showGameOver(){
        System.out.println("Game Over!");
    }

}
