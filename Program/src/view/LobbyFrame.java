package view;

import controller.LanController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LobbyFrame extends JFrame {

    private LanController lanController;
    private JList listOfPlayers;
    private DefaultListModel<String> listModel;
    int flag = 0;

    public LobbyFrame() {
    }

    public LobbyFrame(LanController lanController) {
        this.lanController = lanController;
    }

    public void initFrame(int amountOfConnectedClients) {
        setTitle("Lobby");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        listModel = new DefaultListModel<>();
        listOfPlayers = new JList<>(listModel);
        listOfPlayers.setFont(new Font("Arial", Font.BOLD, 20));

        panel.add(new JScrollPane(listOfPlayers), BorderLayout.CENTER);

        JButton startGameButton = new JButton();
        if (amountOfConnectedClients == 0) {
            startGameButton.setText("Start Game");
            startGameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    lanController.startGame();
                    dispose();
                }
            });
        }
        else {
            startGameButton.setText("Waiting for host...");
            startGameButton.setEnabled(false);
        }

        panel.add(startGameButton, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }


    public void appendLobby(String userNameAndColor){
        if (flag == 0) {
            listModel.addElement("Host and Player:" + (flag + 1));
            listModel.addElement("Name - "  + userNameAndColor + "\n");
        }
        else {
            listModel.addElement("Player " + (flag + 1));
            listModel.addElement("Name - "  + userNameAndColor + "\n");
        }
        flag++;
    }
}
