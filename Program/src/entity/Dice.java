package entity;

import javax.swing.ImageIcon;
import static utilities.Constants.DiceImages.*;

public class Dice {

	private int value = 1;
	private ImageIcon[] diceImages = {WHITE_1, WHITE_2, WHITE_3, WHITE_4, WHITE_5, WHITE_6};
	private ImageIcon currentValueImage;


	public Dice() {
		currentValueImage = diceImages[value - 1];
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

		lblDice2.setIcon(WHITE_1);
		lblDice1.setIcon(WHITE_1);

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

		//playerList.getActivePlayer().checkPlayerRank();
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
		value = (int) (Math.random() * 6) + 1;
        currentValueImage = diceImages[value - 1];
        return value;
	}

	public int getValue() {
        return value;
    }
	public ImageIcon getCurrentValueImage() {
        return currentValueImage;
    }
}