package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import javax.swing.*;

import utilities.Constants;
import view.GamePanels;
import controller.lan.GameClient;
import controller.lan.GameServer;
import entity.player.PlayerList;
import utilities.BackgroundMusic;
import view.Introduction;

/**
 * First screen which entity.player sees, here he is able to choose the amount of players and
 * what names and colors the players will have during the game.
 * @author Aevan Dino
 *
 */
public class StartingScreen extends JFrame implements Runnable {

	private BackgroundMusic bgm = new BackgroundMusic();
	private PlayerList playerList = new PlayerList();
	private GamePanels mainWindow = new GamePanels(bgm);

	private JButton btnConfirm = new JButton("Confirm");
	private JButton btnStartGame = new JButton("Start Game");
	private JButton btnReset = new JButton("Reset");

	private ImageIcon imgBackground = Constants.BoardImages.getStartMenuImage();
	private Font fontRadioButtons = new Font("Gabriola", Font.PLAIN, 24);
	private Font fontHeader = new Font("Gabriola", Font.BOLD, 92); 
	private Font fontLabel = new Font("Gabriola", Font.BOLD, 42);
	private Font fontLabelPlayer = new Font("Gabriola", Font.BOLD, 30);

	private JPanel pnlPlayerInfo = new JPanel();

	private JRadioButton[] radioButtons = new JRadioButton[4];
	private ButtonGroup btnGroup = new ButtonGroup();

	private JLabel lblPlayer = new JLabel("How many players?");
	private JLabel lblBackground = new JLabel("", imgBackground, JLabel.CENTER);
	private JLabel lblRise = new JLabel("RISE");

	private JLabel[] playerLabels = new JLabel[4];

	private JTextField[] playerTf = new JTextField[4];

	private String[] colors = new String[]  { "RED", "GREEN", "ORANGE", "YELLOW", "CYAN", "MAGENTA" };
	private JComboBox<String> availablePlayerColors = new JComboBox<>(colors);
	private JComboBox[] playerColors = new JComboBox[4];

	/**
	 * Mute button
	 */
	private JCheckBox mute = new JCheckBox("Music On");

	/**
	 * Integers
	 */
	private int amountOfPlayers;
	private boolean isNetwork;
	private int activePlayers = 1;

	private JFrame lobbyFrame;
	private JList listOfPlayers;
	private DefaultListModel<String> listModel;
	private GameServer gameServer;

	/**
	 * Used to start the program
	 * @param args
	 */
	public static void main(String[] args) {
		StartingScreen su = new StartingScreen();
		 Thread t = new Thread(su);
		 t.start();
	}

	@Override
	public void run() {
		initializeGUI();
		repaint();
	}

	public PlayerList setUpTest(){
		playerList.addNewPlayer("Test1", (String) playerColors[0].getSelectedItem());
		playerList.addNewPlayer("Test2", (String) playerColors[1].getSelectedItem());
		return playerList;
	}
	/**
	 * Method to check if the OS is MacOS
	 * Note: Temporary solution to fix the font size on MacOS, should be moved to UTIL class.
	 * @return boolean
	 */
	private boolean isMacOS() {
		String os = System.getProperty("os.name");
		return os.contains("Mac");
	}

	/**
	 * Method to initilize the GUI.
	 */
	public void initializeGUI() {
		if (isMacOS()){ // Temporary solution to fix the font size on MacOS, should be moved to UTIL class.
			fontRadioButtons = new Font("Arial", Font.PLAIN, 24);
			fontHeader = new Font("Arial", Font.BOLD, 72);
			fontLabel = new Font("Arial", Font.BOLD, 30);
			fontLabelPlayer = new Font("Arial", Font.BOLD, 20);
		}
		bgm.startMusic();

		createFrame();
		instantiateLabels();

		bgm.startMusic();
		chooseLocalOrNetwork();
	}
	public void instantiateLabels(){
		for(int i = 0;i < 4;i++){
			JLabel lblPlayerIndex = new JLabel("Player" + (i+1) + ":");
			JTextField tfPlayer = new JTextField("Name" + (i+1) + "...");
			availablePlayerColors = new JComboBox<String>(colors);
			playerLabels[i] = lblPlayerIndex;
			playerTf[i] = tfPlayer;
			playerColors[i] = availablePlayerColors;
		}
	}
	public void chooseLocalOrNetwork() {

		JLabel lblPickLANOrLocal = new JLabel("LAN or Local?");
		lblPickLANOrLocal.setFont(fontLabel);
		lblPickLANOrLocal.setBounds(355, 175, 300, 200);

		JButton btnLAN = new JButton("LAN");
		btnLAN.setOpaque(false);

		JButton btnLocal = new JButton("Local");
		btnLocal.setOpaque(false);

		btnLAN.setBounds(375, 315, 150, 30);
		btnLocal.setBounds(375, 365, 150, 30);

		lblBackground.add(btnLAN);
		lblBackground.add(btnLocal);
		lblBackground.add(lblPickLANOrLocal);

		ActionListener buttonActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblBackground.remove(btnLAN);
				lblBackground.remove(btnLocal);
				lblBackground.remove(lblPickLANOrLocal);
				lblBackground.revalidate();
				lblBackground.repaint();

				if (e.getSource() == btnLAN) {
                    setUpLAN();
					isNetwork = true;
                } else if (e.getSource() == btnLocal) {
                    setUpLocal();
                }
			}
		};
		btnLAN.addActionListener(buttonActionListener);
		btnLocal.addActionListener(buttonActionListener);
	}

	public void setUpLAN() {

		JButton btnCreateGame = new JButton("Create Game");
		btnCreateGame.setOpaque(false);

		JButton btnJoinGame = new JButton("Join Game");
		btnJoinGame.setOpaque(false);

		btnCreateGame.setBounds(375, 315, 150, 30);
		btnJoinGame.setBounds(375, 365, 150, 30);

		lblBackground.add(btnCreateGame);
		lblBackground.add(btnJoinGame);

		ActionListener buttonActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblBackground.remove(btnCreateGame);
				lblBackground.remove(btnJoinGame);
				lblBackground.revalidate();
				lblBackground.repaint();

				if (e.getSource() == btnCreateGame) {
                    createGame();
                } else if (e.getSource() == btnJoinGame) {
                    joinGame();
                }
			}
		};
		btnCreateGame.addActionListener(buttonActionListener);
		btnJoinGame.addActionListener(buttonActionListener);
	}

	public void createGame() {
		lblPlayer.setFont(fontLabel);
		lblPlayer.setBounds(315, 175, 300, 200);

		createRadioButtons();

		int i = 0;

		playerLabels[i].setBounds(375, 330 + i * 40, 150, 50);
		playerLabels[i].setFont(fontLabelPlayer);
		playerLabels[i].setText("Enter name: ");

		playerTf[i].setBounds(375, 360 + i * 40, 150, 30);
		playerTf[i].addMouseListener(new MouseAction());

		playerColors[i].setBounds(530, 360 + i * 40, 100, 30);

		pnlPlayerInfo.add(playerLabels[i]);
		pnlPlayerInfo.add(playerTf[i]);
		pnlPlayerInfo.add(playerColors[i]);

		JButton btnHostGame = new JButton("Host Game");
		btnHostGame.setOpaque(false);
		btnHostGame.setBounds(350, 530, 200, 40);
		btnHostGame.addActionListener(e -> startLobby());

		lblBackground.add(btnHostGame);
		lblBackground.add(pnlPlayerInfo);
		lblBackground.add(btnStartGame);
	}

	public void startLobby() {
		String hostName = playerTf[0].getText();
		//assignAmountOfPlayers();

		lobbyFrame = new JFrame("Lobby");
		lobbyFrame.setSize(400, 200);
		lobbyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());

		listModel = new DefaultListModel<>();
		listOfPlayers = new JList<>(listModel);
		listOfPlayers.setFont(new Font("Arial", Font.BOLD, 20));

		panel.add(new JScrollPane(listOfPlayers), BorderLayout.CENTER);

		JButton startGameButton = new JButton("Start Game");
		startGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createLANPlayers();
				gameServer.setMainWindow(mainWindow);
				gameServer.assignPlayerListToEachClient(playerList);
				gameServer.sendBoardToEachClient();
				lobbyFrame.dispose();
			}
		});

		panel.add(startGameButton, BorderLayout.SOUTH);

		lobbyFrame.add(panel);
		lobbyFrame.setVisible(true);

		listModel.addElement("Host and Player " + activePlayers + ": " + hostName + "\n");

		try {
			startServerAndConnectAsHost(hostName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void startUpLANGame() {
		mainWindow.addPlayer(playerList);
		mainWindow.startboard();
		dispose();
	}

	public void setPlayerList(PlayerList playerList) {
		this.playerList = playerList;
	}

	public void createLANPlayers(){
		for (int i = 0; i < listModel.size(); i++) {
			String element = listModel.getElementAt(i);
			String[] parts = element.split(":");
			if (parts.length > 1) {
				String username = parts[1].trim();
				playerList.addNewPlayer(username, "RED");
			}
		}
	}

	public void startServerAndConnectAsHost(String hostName) throws IOException {
        gameServer = new GameServer(this, 9090);
		Thread gameServerThread = new Thread(gameServer);
		gameServerThread.start();

		GameClient gameClient = new GameClient(this, hostName, "0.0.0.0", 9090);
		gameClient.run();
	}

	public void joinGame() {
		playerLabels[activePlayers].setBounds(375, 330 + activePlayers * 40, 150, 50);
		playerLabels[activePlayers].setFont(fontLabelPlayer);
		playerLabels[activePlayers].setText("Enter name: ");

		playerTf[activePlayers].setBounds(375, 360 + activePlayers * 40, 150, 30);
		playerTf[activePlayers].addMouseListener(new MouseAction());

		playerColors[activePlayers].setBounds(530, 360 + activePlayers * 40, 100, 30);

		JButton btnJoinGame = new JButton("Join Game");
		btnJoinGame.setOpaque(false);
		btnJoinGame.setBounds(350, 530, 200, 40);
		btnJoinGame.addActionListener(e -> joinLobby(playerTf[activePlayers].getText()));

		pnlPlayerInfo.add(btnJoinGame);
		pnlPlayerInfo.add(playerLabels[activePlayers]);
		pnlPlayerInfo.add(playerTf[activePlayers]);
		pnlPlayerInfo.add(playerColors[activePlayers]);

		lblBackground.add(pnlPlayerInfo);
	}

	public void joinLobby(String playerName) {
		GameClient gameClient = new GameClient(this, playerName, "0.0.0.0", 9090);
		gameClient.run();

		pnlPlayerInfo.removeAll();

		JLabel lblConnected = new JLabel("Connection successful!");
		lblConnected.setFont(new Font("Arial", Font.BOLD, 20));
		lblConnected.setBounds(335, 315, 250, 30);
		JLabel lblWaiting = new JLabel("Waiting for host to start game...");
		lblWaiting.setFont(new Font("Arial", Font.BOLD, 20));
		lblWaiting.setBounds(335, 345, 250, 30);

		pnlPlayerInfo.add(lblConnected);
		pnlPlayerInfo.add(lblWaiting);

		lblBackground.add(pnlPlayerInfo);
		lblBackground.revalidate();
		lblBackground.repaint();
	}

	public void appendLobby(String playerName) {

		if (!Objects.equals(playerName, playerTf[0].getText())){
			listModel.addElement("Player " + activePlayers +  ": " + playerName + "\n");
		}
		activePlayers++;
	}

	public void setUpLocal() {

		/**
		 * JLabel reading "How many players?"
		 */
		lblPlayer.setFont(fontLabel);
		lblPlayer.setBounds(315, 175, 300, 200);

		/**
		 * Create three JRadioButtons
		 */
		createRadioButtons();

		/**
		 * Confirm button
		 */
		btnConfirm.setBounds(375, 315, 150, 30);
		btnConfirm.addActionListener(new ButtonListener());

		/**
		 * Create players
		 */
		CreatePlayers();

		/**
		 * Start game button
		 */
		btnStartGame.setBounds(350, 530, 200, 40);
		btnStartGame.setVisible(false);
		btnStartGame.addActionListener(new ButtonListener());

		/**
		 * Rest button
		 */
		btnReset.setBounds(375, 575, 150, 30);
		btnReset.setVisible(false);
		btnReset.addActionListener(new ButtonListener());

		/**
		 * Mute button
		 */
		mute.setBounds(2, 740, 150, 35);
		mute.setForeground(Color.white);
		mute.setFont(fontRadioButtons);
		mute.setOpaque(false);
		mute.addActionListener(new ButtonListener());

		/**
		 * Adding stuff to background label
		 */
		lblBackground.add(btnConfirm);
		lblBackground.add(pnlPlayerInfo);
		lblBackground.add(btnReset);
		lblBackground.add(btnStartGame);
		lblBackground.add(mute);
		lblBackground.add(lblRise);
		lblBackground.add(lblPlayer);
		add(lblBackground);
	}

	public void createFrame() {
		setSize(900, 830);
		setTitle("Choose Player!");
		setLayout(null);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		/**
		 * JPanel for information about players
		 */
		pnlPlayerInfo.setBounds(0, 0, 900, 830);
		pnlPlayerInfo.setOpaque(false);
		pnlPlayerInfo.setLayout(null);

		/**
		 * Label used to create a background
		 */
		lblBackground.setBounds(0, 0, 900, 830);
		lblBackground.setIcon(imgBackground);
		lblBackground.setLayout(null);

		/**
		 *  Header reading "RISE"
		 */
		lblRise.setFont(fontHeader);
		lblRise.setBounds(375, 125, 175, 200);
		lblBackground.add(lblRise);

		lblBackground.add(lblRise);
		lblBackground.add(lblPlayer);
		add(lblBackground);
	}

	/**
	 * Methods create radio buttons using for-loop
	 */
	public void createRadioButtons() {
		for (int i = 0; i < 3; i++) {
			JRadioButton btnRadio = new JRadioButton((i + 2) + "");
			btnRadio.setBounds(375 + i * 65, 275, 50, 50);
			btnRadio.setFont(fontRadioButtons);
			btnRadio.setOpaque(false);
			btnGroup.add(btnRadio);
			radioButtons[i] = btnRadio;
			lblBackground.add(btnRadio);
		}
	}
	
	/**
	 * Creates all players, textfields, labels and color choice boxes
	 */
	public void CreatePlayers() {
		for (int i = 0; i < 4; i++) {
			playerLabels[i].setBounds(280, 360 + i * 40, 150, 50);
			playerLabels[i].setFont(fontLabelPlayer);
			playerLabels[i].setVisible(false);

			playerTf[i].setBounds(375, 360 + i * 40, 150, 30);
			playerTf[i].setVisible(false);
			playerTf[i].addMouseListener(new MouseAction());

			playerColors[i].setBounds(530, 360 + i * 40, 100, 30);
			playerColors[i].setVisible(false);

			pnlPlayerInfo.add(playerLabels[i]);
			pnlPlayerInfo.add(playerTf[i]);
			playerColors[i].setSelectedIndex(i);
			pnlPlayerInfo.add(playerColors[i]);
		}
	}

	/**
	 * Buttonlistener class, listens for clicks.
	 * @author Aevan Dino
	 */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnReset) {
				btnPressed(3, false);
			}

			if (e.getSource() == mute) {
				if (mute.getText().contains("n")) {
					mute.setText("Music Off");
					bgm.pauseMusic();
				} else {
					mute.setText("Music On");
					bgm.startMusic();
				}
			}

			if (e.getSource() == btnConfirm) {

				if (radioButtons[0].isSelected()) {
					btnPressed(2, true);
					amountOfPlayers = 2;
				} else if (radioButtons[1].isSelected()) {
					btnPressed(3, true);
					amountOfPlayers = 3;

				} else if (radioButtons[2].isSelected()) {
					btnPressed(4, true);
					amountOfPlayers = 4;
				}
			}

			if (e.getSource() == btnStartGame) {

				if(playerTf[0].getText().length()==0 || playerTf[1].getText().length()==0 || playerTf[2].getText().length()==0 || playerTf[3].getText().length()==0) {
					JOptionPane.showMessageDialog(null, "All players must have a name");
				} else {

					switch(amountOfPlayers) {

					case 2:
						if(playerColors[0].getSelectedItem().equals(playerColors[1].getSelectedItem())) {
							JOptionPane.showMessageDialog(null, "Two players are not allowed to have the same color");
						} else {
							if(!isNetwork) {
								startUpLocalGame();
							}
						}
						break;

					case 3:
						if(playerColors[0].getSelectedItem().equals(playerColors[1].getSelectedItem()) 
								|| playerColors[2].getSelectedItem().equals(playerColors[0].getSelectedItem())) {
							JOptionPane.showMessageDialog(null, "Two or more players are not allowed to have the same color");
						} else {
							if(!isNetwork) {
								startUpLocalGame();
							}
						}
						break;

					case 4:
						if(playerColors[0].getSelectedItem().equals(playerColors[1].getSelectedItem()) 
								|| playerColors[2].getSelectedItem().equals(playerColors[3].getSelectedItem())
								|| playerColors[0].getSelectedItem().equals(playerColors[3].getSelectedItem())) {
							JOptionPane.showMessageDialog(null, "Two or more players are not allowed to have the same color");
						} else {
							if(!isNetwork) {
								startUpLocalGame();
							}
						}
						break;
					}
				}
			}
		}

		/**
		 * Method called when entity.player clicks start game
		 */
		public void startUpLocalGame() {
			createNewUsers();
			mainWindow.addPlayer(playerList);
			mainWindow.startboard();
			dispose();
			Introduction intro = new Introduction();
		}
		
		/**
		 * Creates the right amount of players.
		 */
		private void createNewUsers() {

			for (int i = 0; i < amountOfPlayers; i++) {

				if (playerTf[i].getText().length()>=10) {
					playerTf[i].setText(playerTf[i].getText().substring(0, 10));
				}
				playerList.addNewPlayer(playerTf[i].getText(), (String) playerColors[i].getSelectedItem());
			}

		}

		/**
		 * Whenever entity.player chooses to reset the start screen
		 * @param amountOfPlayers, how many players to draw
		 * @param bool, boolean indicating whether or not components should be visible.
		 */
		public void btnPressed(int amountOfPlayers, boolean bool) {
			for (int i = 0; i < amountOfPlayers; i++) {
				playerLabels[i].setVisible(bool);
				playerTf[i].setVisible(bool);
				playerColors[i].setVisible(bool);
			}
			btnStartGame.setVisible(bool);
			btnReset.setVisible(bool);
			btnConfirm.setEnabled(!bool);

		}
	}

	/**
	 * MouseClickedListener for the name inserting so the text disappear when the entity.player clicks.
	 */
	private class MouseAction implements MouseListener{
		int counter1 = 0, counter2 = 0, counter3 =0, counter4=0;
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == playerTf[0]) {
				if(counter1<1) {
					counter1++;
					playerTf[0].setText(null);
				}
			}
			if(e.getSource() == playerTf[1]) {
				if(counter2<1) {
					counter2++;
					playerTf[1].setText(null);
				}
			}
			if(e.getSource() == playerTf[2]) {
				if(counter3<1) {
					counter3++;
					playerTf[2].setText(null);
				}
			}
			if(e.getSource() == playerTf[3]) {
				if(counter4<1) {
					counter4++;
					playerTf[3].setText(null);
				}
			}
		}
		/**
		 * Nothing will happen with the other implemented methods. Methods must be implemented by MouseListener.
		 */
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
		public void mousePressed(MouseEvent e) {
		}
		public void mouseReleased(MouseEvent e) {
		}
	}
}