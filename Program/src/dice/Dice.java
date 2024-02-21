package dice;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import board.Board;
import cheat.CheatGui; //May be needed for testing in future 
import eastSidePanels.EastSidePanel;
import events.ManageEvents;
import player.PlayerList;
import westSidePanel.WestSidePanel;

/**
 * @author Muhammad Abdulkhuder, Aevan Dino, Sebastian Viro, Seth Oberg
 *
 */
public class Dice extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private ShowPlayersTurn showPlayersTurn;
	private Board board;
	private PlayerList playerList;
	private WestSidePanel westSidePnl;
	private EastSidePanel eastSidePnl = new EastSidePanel();

	private Thread movePlayerThread;
	private ManageEvents manageEvents;

	public JButton btnEndTurn = new JButton("End Turn");
	public JButton btnRollDice = new JButton("Roll Dice");

	private JLabel lblDice1 = new JLabel();
	private JLabel lblDice2 = new JLabel();

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private ImageIcon faceToShow, showDice;
	private Image resizedImage;

	private int diceWidth = (screenSize.width) / 20;
	private int diceHeight = (screenSize.height) / 10;
	private int roll;
	private CheatGui cheat = new CheatGui(this);
	
	/**
	 * @param playerList method used for updating the list of players 
	 */
	public void addPlayerList(PlayerList playerList) {
		this.playerList = playerList;
		
		showPlayersTurn.uppdateGUI(playerList.getActivePlayer().getName(),
				playerList.getActivePlayer().getPlayerColor());
		
		manageEvents = new ManageEvents(board, playerList, westSidePnl, this, eastSidePnl);
	}
	
	
	/**
	 * @param board The board object 
	 * @param playerList a list containing all the players in the game
	 * @param westSidePanel panel containing all the information about the tiles and the history of all the events
	 * @param eastSidePnl panel containing all the information about the players and their properties 
	 */
	public Dice(Board board, PlayerList playerList, WestSidePanel westSidePanel, EastSidePanel eastSidePnl) {
		this.board = board;
		this.playerList = playerList;
		this.westSidePnl = westSidePanel;
		this.eastSidePnl = eastSidePnl;
		initializePanel();
	}

	/**
	 * calls the method that initializes the panel
	 */
	public Dice() {
		initializePanel();
	}

	/**
	 * initializes Panel
	 */
	public void initializePanel() {
		setPreferredSize(new Dimension(650, 120));
		setLayout(new FlowLayout());
		setOpaque(false);
		
		showPlayersTurn = new ShowPlayersTurn("Player");
		add(showPlayersTurn);
		add(lblDice1);
		add(lblDice2);

		btnRollDice.setFont(new Font("Algerian", Font.PLAIN, 14));
		add(btnRollDice);

		btnRollDice.addActionListener(this);

		faceToShow = new ImageIcon("DicePictures/faceValue1White.png");
		resizedImage = faceToShow.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH);
		showDice = new ImageIcon(resizedImage);
		lblDice2.setIcon(showDice);
		lblDice1.setIcon(showDice);

		btnEndTurn.setFont(new Font("Algerian", Font.PLAIN, 14));
		btnEndTurn.addActionListener(this);

		add(btnEndTurn);
		add(cheat); //Enable for testing with cheater.
		btnEndTurn.setEnabled(false);
	}
	/**
	 * Action Listener that handles what happens if the buttons are pressed
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRollDice) {
			rollDice();
		}
		if (e.getSource() == btnEndTurn) {
			endTurn();
		}
	}
	/**
	 * @param roll the number of the dice
	 * @return the image of the dice
	 */
	public ImageIcon setDiceImage(int roll){
		return switch (roll) {
            case 1 -> new ImageIcon("DicePictures/faceValue1White.png");
            case 2 -> new ImageIcon("DicePictures/faceValue2White.png");
            case 3 -> new ImageIcon("DicePictures/faceValue3White.png");
            case 4 -> new ImageIcon("DicePictures/faceValue4White.png");
            case 5 -> new ImageIcon("DicePictures/faceValue5White.png");
            case 6 -> new ImageIcon("DicePictures/faceValue6White.png");
            default -> null;
        };
	}
	/**
	 * Rolls the dice and sets the face value of the dice to a random number between 1 and 6
	 */
	public void rollDice(){
		int faceValueDiceOne = roll();
		int faceValueDiceTwo = roll();

		faceToShow = setDiceImage(faceValueDiceOne);

		resizedImage = faceToShow.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH);
		showDice = new ImageIcon(resizedImage);
		lblDice1.setIcon(showDice);

		faceToShow = setDiceImage(faceValueDiceTwo);

		resizedImage = faceToShow.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH);
		showDice = new ImageIcon(resizedImage);
		lblDice2.setIcon(showDice);

		if (faceValueDiceOne == faceValueDiceTwo) {
			setRoll(((faceValueDiceOne + faceValueDiceTwo) * 2)); //TODO: Weird logic, check if it's correct
			//westSidePnl.append(playerList.getActivePlayer().getName() + " Rolled a dubble: " + getRoll() + "\n"); //TODO NOT IN USE
		} else {
			setRoll(((faceValueDiceOne + faceValueDiceTwo)));
			//westSidePnl.append(playerList.getActivePlayer().getName() + " Rolled a: " + getRoll() + "\n"); //TODO NOT IN USE
		}

		playerList.getActivePlayer().checkPlayerRank();
		manageEvents.setRoll(this);

		movePlayerThread = new Thread(new LoopThread(getRoll()));
		movePlayerThread.start();

		goEvent();

		eastSidePnl.addPlayerList(playerList);

		btnRollDice.setEnabled(false);
	}
	/**
	 * When a player ends their turn
	 * If the next player is in jail they will not have the ability to roll the
	 * dice and will only have the ability to end their turn if they have not paid the bail
	 * If the player is not in jail they can roll the dice
	 */
	public void endTurn(){
		playerList.switchToNextPlayer();

		showPlayersTurn.uppdateGUI(playerList.getActivePlayer().getName(),
				playerList.getActivePlayer().getPlayerColor());

		if (playerList.getActivePlayer().isPlayerInJail()) {
			btnRollDice.setEnabled(false);
			btnEndTurn.setEnabled(true);
			manageEvents.newEvent(board.getDestinationTile(playerList.getActivePlayer().getPosition()),
					playerList.getActivePlayer());
		}

		else if (!playerList.getActivePlayer().isPlayerInJail()) {
			btnRollDice.setEnabled(true);
			btnEndTurn.setEnabled(false);
		}

		eastSidePnl.addPlayerList(playerList);
		eastSidePnl.setTab();
		westSidePnl.getEventPanel().resetEventPanel();
	}
	/**
	 * @param i method used for Testing
	 * it moves the player to a specific index
	 */
	public void moveWCheat(int i) {
		setRoll(i);
		playerList.getActivePlayer().checkPlayerRank();
		board.removePlayer(playerList.getActivePlayer());
		playerList.getActivePlayer().setPosition(getRoll());
		board.setPlayer(playerList.getActivePlayer());
		
		manageEvents.setRoll(this);
		goEvent();
		manageEvents.newEvent(board.getDestinationTile(playerList.getActivePlayer().getPosition()),
				playerList.getActivePlayer());
		eastSidePnl.addPlayerList(playerList);
	}


	/**
	 * To free the prisoner
	 */
	public void activateRollDice() {
		btnRollDice.setEnabled(true);
		btnEndTurn.setEnabled(false);
	}

	/**
	 * Ends the turn if player is eliminated
	 */
	public void endTurnIfPlayerEliminated() {
		btnRollDice.setEnabled(true);
		btnEndTurn.setEnabled(false);
	}

	/**
	 * @param playerList
	 */
	public void setPlayerList(PlayerList playerList) {
		this.playerList = playerList;
	}
	
	/**
	 * @return number of total roll
	 */
	public int getRoll() {
		return roll;
	}

	/**
	 * Rolls the dice
	 * @return
	 */
	public int roll(){
		return (int) (Math.random() * (7 - 1) + 1);
	}
	/**
	 * @param  roll number of total roll
	 */
	public void setRoll(int roll) {
		this.roll = roll;
	}

	/**
	 * @author Seth �berg, Muhammad Abdulkhuder
	 * Moves the player with a thread.
	 *
	 */
	private class LoopThread implements Runnable {
		public LoopThread(int index) {
			setRoll(index);
		}

		public void run() {

			for (int i = 0; i < getRoll(); i++) {
				board.removePlayer(playerList.getActivePlayer());
				playerList.getActivePlayer().setPosition(1);
				board.setPlayer(playerList.getActivePlayer());

				if (i == (getRoll() - 1)) {
					manageEvents.newEvent(board.getDestinationTile(playerList.getActivePlayer().getPosition()),
							playerList.getActivePlayer());
					eastSidePnl.addPlayerList(playerList);
					btnEndTurn.setEnabled(true);

				}

				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}

	/**
	 * If a player passes go.
	 */
	private void goEvent() {

		if (playerList.getActivePlayer().passedGo()) {

			playerList.getActivePlayer().increaseBalance(200);
			playerList.getActivePlayer().increaseNetWorth(200);

			//westSidePnl.append("Passed Go and received 200 GC\n"); //TODO NOT IN USE
			playerList.getActivePlayer().resetPassedGo();
		}
	}
}