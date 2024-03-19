package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import utilities.Constants;
import utilities.BackgroundMusic;
import view.messageGui.Introduction;

import static controller.LanController.createAndConnectClient;
import static utilities.Constants.GameWindow.*;

/**
 * First screen which entity.player sees, here he is able to choose the amount of players and
 * what names and colors the players will have during the game.
 * @author Aevan Dino
 *
 */
public class StartingScreen extends JFrame {


	private BackgroundMusic bgm = new BackgroundMusic();
	private LanController lanController;
	private BoardController boardController;
    private JButton btnLocal;
    private JButton btnLAN;
	private JButton btnConfirm = new JButton("Confirm");
	private JButton btnStartGame = new JButton("Start Game");
	private JButton btnReset = new JButton("Reset");

	private JPanel pnlPlayerInfo = new JPanel();

	private JRadioButton[] radioButtons = new JRadioButton[3];
	private ButtonGroup btnGroup = new ButtonGroup();

	private JLabel lblPlayer = new JLabel("How many players?");
	private JLabel lblBackground = new JLabel("", Constants.BoardImages.getStartMenuImage(), JLabel.CENTER);
	private JLabel lblRise = new JLabel("RISE");

	private JLabel[] playerLabels = new JLabel[4];

	private JTextField[] playerTf = new JTextField[4];

	private String[] colors = new String[]  { "RED", "GREEN", "ORANGE", "YELLOW", "CYAN", "MAGENTA" };
	private JComboBox<String> availablePlayerColors = new JComboBox<>(colors);
	private JComboBox[] playerColors = new JComboBox[4];
	private JCheckBox mute = new JCheckBox("Music On");

    private boolean localSetUpOK = false;

	private int amountOfPlayers;
	private int activePlayers = 1;

	/**
	 * Used to start the program
	 * @param args
	 */
	public static void main(String[] args) {
		new StartingScreen();
	}


	public StartingScreen(){
		initializeGUI();
		repaint();
	}

	/**
	 * Method to initilize the GUI.
	 */
	public void initializeGUI() {
		createFrame();
		instantiateLabels();
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

        btnLAN = new JButton("LAN");
		btnLAN.setOpaque(false);

        btnLocal = new JButton("Local");
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


		playerLabels[0].setBounds(375, 330, 150, 50);
		playerLabels[0].setFont(fontLabelPlayer);
		playerLabels[0].setText("Enter name: ");

		playerTf[0].setBounds(375, 360, 150, 30);
		playerTf[0].addMouseListener(new MouseAction());

		playerColors[0].setBounds(530, 360, 100, 30);

		pnlPlayerInfo.add(playerLabels[0]);
		pnlPlayerInfo.add(playerTf[0]);
		pnlPlayerInfo.add(playerColors[0]);

		JButton btnHostGame = new JButton("Host Game");
		btnHostGame.setOpaque(false);
		btnHostGame.setBounds(350, 530, 200, 40);
		btnHostGame.addActionListener(e -> startUpLANGame());

		lblBackground.add(btnHostGame);
		lblBackground.add(pnlPlayerInfo);
		lblBackground.add(btnStartGame);
	}


	public void startUpLANGame() {
		String playerName = playerTf[0].getText();
		String playerColor = (String) playerColors[0].getSelectedItem();
		lanController = new LanController();
		lanController.startServerAndConnectAsHost(playerName, playerColor);
		dispose();
	}


	public void joinLobby() {
		String playerName = playerTf[0].getText();
		String playerColor = (String) playerColors[0].getSelectedItem();
		createAndConnectClient(playerName, playerColor);
		dispose();
	}


	public void joinGame() {
		playerLabels[0].setBounds(375, 330, 150, 50);
		playerLabels[0].setFont(fontLabelPlayer);
		playerLabels[0].setText("Enter name: ");

		playerTf[0].setBounds(375, 360, 150, 30);
		playerTf[0].addMouseListener(new MouseAction());

		playerColors[0].setBounds(530, 360, 100, 30);

		JButton btnJoinGame = new JButton("Join Game");
		btnJoinGame.setOpaque(false);
		btnJoinGame.setBounds(350, 530, 200, 40);
		btnJoinGame.addActionListener(e -> joinLobby());

		pnlPlayerInfo.add(btnJoinGame);
		pnlPlayerInfo.add(playerLabels[0]);
		pnlPlayerInfo.add(playerTf[0]);
		pnlPlayerInfo.add(playerColors[0]);

		lblBackground.add(pnlPlayerInfo);
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

        localSetUpOK = true;
	}

	public void createFrame() {
		setSize(900, 830);
		setTitle("Choose Player!");
		setLayout(null);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		lblBackground.setIcon(Constants.BoardImages.getStartMenuImage());
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
				toggleMusic();
			}
			if (e.getSource() == btnConfirm) {
				processConfirmation();
			}
			if (e.getSource() == btnStartGame) {
				startGame();
			}
		}

		private void toggleMusic() {
			if (mute.getText().contains("n")) {
				mute.setText("Music Off");
				bgm.pauseMusic();
			} else {
				mute.setText("Music On");
				bgm.startMusic();
			}
		}

		private void processConfirmation() {
			if (radioButtons[0].isSelected()) {
				setupPlayers(2);
			} else if (radioButtons[1].isSelected()) {
				setupPlayers(3);
			} else if (radioButtons[2].isSelected()) {
				setupPlayers(4);
			}
		}

		private void setupPlayers(int numPlayers) {
			btnPressed(numPlayers, true);
			amountOfPlayers = numPlayers;
		}

		private void startGame() {
			if (!allPlayersHaveNames()) {
				JOptionPane.showMessageDialog(null, "All players must have a name");
			} else {
				if (!playerColorsAreUnique()) {
					JOptionPane.showMessageDialog(null, "Two or more players are not allowed to have the same color");
				} else {
					startUpLocalGame();
				}
			}
		}

		private boolean allPlayersHaveNames() {
			for (JTextField tf : playerTf) {
				if (tf.getText().isEmpty()) {
					return false;
				}
			}
			return true;
		}

		private boolean playerColorsAreUnique() {
			for (int i = 0; i < amountOfPlayers; i++) {
				for (int j = i + 1; j < amountOfPlayers; j++) {
					if (playerColors[i].getSelectedItem().equals(playerColors[j].getSelectedItem())) {
						return false;
					}
				}
			}
			return true;
		}

		/**
		 * Method called when entity.player clicks start game
		 */
		public void startUpLocalGame() {
			boardController = new BoardController();
			createNewUsers();
			boardController.addPlayerTabs();
			boardController.startBoard();
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
				boardController.addPlayerToList(playerTf[i].getText(), (String) playerColors[i].getSelectedItem());
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
		private int[] counters = new int[4];

		public void mouseClicked(MouseEvent e) {
			for (int i = 0; i < playerTf.length; i++) {
				if (e.getSource() == playerTf[i]) {
					if (counters[i] < 1) {
						counters[i]++;
						playerTf[i].setText(null);
					}
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

    public JButton getBtnLocal() {
        return btnLocal;
    }
    public JButton getBtnLAN(){
        return btnLAN;
    }
    public JRadioButton[] getRadioButtons() {
        return radioButtons;
    }
    public LanController getLanController() {
        return lanController;
    }
    public boolean getLocalSetUpOK() {
        return localSetUpOK;
    }
    public int getAmountOfPlayers() {
        return amountOfPlayers;
    }
    public JButton getBtnConfirm(){
        return btnConfirm;
    }
    public JButton getBtnStartGame(){
        return btnStartGame;
    }
}