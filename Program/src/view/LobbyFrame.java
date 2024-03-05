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


    public LobbyFrame(LanController lanController) {
        this.lanController = lanController;
        initFrame();
    }

    private void initFrame() {
        setTitle("Lobby");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        listModel = new DefaultListModel<>();
        listOfPlayers = new JList<>(listModel);
        listOfPlayers.setFont(new Font("Arial", Font.BOLD, 20));

        panel.add(new JScrollPane(listOfPlayers), BorderLayout.CENTER);

        JButton startGameButton = new JButton();
        if (lanController.getAmountOfConnectedClients() == 0) {
            startGameButton.setText("Start Game");
            startGameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    lanController.startGame();
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


    public void appendLobby(String userName){
        int size = listModel.getSize();
        if (size == 0) {
            listModel.addElement("Host and Player " + (size + 1) + ": " + userName + "\n");
        }
        else {
            listModel.addElement("Player " + (size + 1) + ": " + userName + "\n");
        }
    }
}
