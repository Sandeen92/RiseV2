package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.*;

import static utilities.Constants.DiceImages.WHITE_1;

public class DicePanel extends JPanel implements ActionListener {

    private BoardController board;
    private JButton btnEndTurn = new JButton("End Turn");
    private JButton btnRollDice = new JButton("Roll Dice");
    private JLabel lblDice1;
    private JLabel lblDice2;
    private JLabel lblPlayer;


    public DicePanel(BoardController board) {
        this.board = board;
        initTurnLabel();
        initPanel();
    }

    public void initPanel(){
        setPreferredSize(new Dimension(650, 120));
        setLayout(new FlowLayout());
        setOpaque(false);


        lblDice1 = new JLabel();
        lblDice2 = new JLabel();
        add(lblDice1);
        add(lblDice2);

        btnRollDice.setFont(new Font("Algerian", Font.PLAIN, 14));
        add(btnRollDice);

        btnRollDice.addActionListener(this);

        lblDice2.setIcon(WHITE_1);
        lblDice1.setIcon(WHITE_1);

        btnEndTurn.setFont(new Font("Algerian", Font.PLAIN, 14));
        btnEndTurn.addActionListener(this);

        add(btnEndTurn);
        btnEndTurn.setEnabled(false);
    }

    public void initTurnLabel() {
        setPreferredSize(new Dimension(250,50));
        setBackground(Color.DARK_GRAY);


        lblPlayer = new JLabel("");
        lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
        lblPlayer.setForeground(Color.white);
        lblPlayer.setVisible(true);
        lblPlayer.setPreferredSize(new Dimension(240,45));
        lblPlayer.setFont(new Font("ALGERIAN", Font.BOLD, 14 ));
        lblPlayer.setBorder(BorderFactory.createLineBorder(Color.white));
        add(lblPlayer);
    }

    public void updateTurnLabel(String playerName, Color color) {
        lblPlayer.setOpaque(true);
        lblPlayer.setBackground(color);
        lblPlayer.setText(playerName+"'s turn");
    }

    public void setDiceImages(ImageIcon dice1, ImageIcon dice2) {
        lblDice1.setIcon(dice1);
        lblDice2.setIcon(dice2);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRollDice) {
            int diceRoll = board.rollDices();
            board.movePlayer(diceRoll);
            btnRollDice.setEnabled(false);
        }
        if (e.getSource() == btnEndTurn) {
            board.endTurn();
            btnRollDice.setEnabled(true);
            btnEndTurn.setEnabled(false);
        }
    }

    public void enableEndTurnBtn(){
        btnEndTurn.setEnabled(true);
    }
}
