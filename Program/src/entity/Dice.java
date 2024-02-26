package entity;

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

import utilities.Constants;
import view.ShowPlayersTurn;
import view.CheatGui; //May be needed for testing in future
import view.eastSidePanels.EastSidePanel;
import controller.ManageEvents;
import entity.player.PlayerList;
import view.*;

import static utilities.Constants.DiceImages.*;


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
	private ImageIcon faceToShow;

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
	 * @param westSidePanel panel containing all the information about the entity.tiles and the history of all the events
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

		lblDice2.setIcon(faceToShow);
		lblDice1.setIcon(faceToShow);

		btnEndTurn.setFont(new Font("Algerian", Font.PLAIN, 14));
		btnEndTurn.addActionListener(this);

		add(btnEndTurn);
		add(cheat); //Enable for testing with cheater.
		btnEndTurn.setEnabled(false);
	}
	/**
	 * Action Listener that handles what happens if the buttons are pressed
	 * @param e the action event
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
	 * sets the image of the dice to the face value of the dice corresponding to the number rolled
	 * Broke out this method to make it easier to read because it looked like donkey poop before :)
	 */
	public ImageIcon setDiceImage(int roll){
		return switch (roll) {
            case 1 -> WHITE_1;
            case 2 -> WHITE_2;
            case 3 -> WHITE_3;
            case 4 -> WHITE_4;
            case 5 -> WHITE_5;
            case 6 -> WHITE_6;
            default -> null;
        };
	}
	/**
	 * Rolls the dice and sets the face value of the dice to a random number between 1 and 6
	 */
	public void rollDice(){
		int faceValueDiceOne = roll();
		int faceValueDiceTwo = roll();

		lblDice1.setIcon(setDiceImage(faceValueDiceOne));
		lblDice2.setIcon(setDiceImage(faceValueDiceTwo));

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
	 * When a entity.player ends their turn
	 * If the next entity.player is in jail they will not have the ability to roll the
	 * dice and will only have the ability to end their turn if they have not paid the bail
	 * If the entity.player is not in jail they can roll the dice
	 */
	public void endTurn(){
		playerList.switchToNextPlayer();
		showPlayersTurn.uppdateGUI(playerList.getActivePlayer().getName(), playerList.getActivePlayer().getPlayerColor());

		if (playerList.getActivePlayer().isPlayerInJail()) {
			btnRollDice.setEnabled(false);
			btnEndTurn.setEnabled(true);
			manageEvents.newEvent(board.getDestinationTile(playerList.getActivePlayer().getPosition()), playerList.getActivePlayer());
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
	 * it moves the entity.player to a specific index
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
	 * Ends the turn if entity.player is eliminated
	 */
	public void endTurnIfPlayerEliminated() {
		btnRollDice.setEnabled(true);
		btnEndTurn.setEnabled(false);
	}

	/**
	 * @param playerList
	 */
	public PlayerList setPlayerList(PlayerList playerList) {
		this.playerList = playerList;
		return playerList;
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
	 * Moves the entity.player with a thread.
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
	 * If a entity.player passes go.
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