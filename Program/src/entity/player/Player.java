package entity.player;

import java.awt.Color;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import entity.tiles.Property;
import entity.tiles.Tavern;
import entity.tiles.Tile;

import static entity.player.PlayerRanks.*;
import static utilities.Constants.PlayerTokenImages.*;

/**
 * Player class deals with everything that has to do with a entity.player.
 * 
 * @author AevanDino, Seth �berg, Muhammad Hasan, Sebastian Viro
 */
public class Player implements Serializable {

	private String name;
	private Boolean isAlive;

	private ImageIcon playerIcon;
	private int counter;
	private int playerIndex;
	private int playerJailCounter = 0;
	private boolean playerIsInJail = false;
	private Color playerColor;

	private PlayerRanks playerRank;

	private int balance;
	private int netWorth;
	private boolean playerPassedgo = false;

	private ArrayList<Property> propertiesOwned;
	private ArrayList<Tile> tilesOwned;
	private ArrayList<Tavern> tavernsOwned;



	public Player(String inPlayerName, ImageIcon playerIcon, int playerIndex) {

		setName(inPlayerName);
		this.playerIcon = playerIcon;
		setIsAlive(true);
		this.playerIndex = playerIndex;


		setPlayerRank(PEASANT);
		this.playerIndex = playerIndex;
		this.tavernsOwned = new ArrayList<>();
		this.propertiesOwned = new ArrayList<>();

		counter = 0;
	}

	public Player(String inPlayerName, ImageIcon playerIcon, Color playerColor, int playerIndex) {
		this.playerColor = playerColor;
		setName(inPlayerName);
		this.playerIcon = playerIcon;
		setIsAlive(true);
		this.playerIndex = playerIndex;

		setBalance(1500);
		setNetWorth(1500);
		setPlayerRank(PEASANT);
		this.playerIndex = playerIndex;
		this.tavernsOwned = new ArrayList<>();
		this.propertiesOwned = new ArrayList<>();

		counter = 0;
	}

	/**
	 * Keep track of how many turns a user has been in jail, if 3 the entity.player gets
	 * out of jail if less than 3 the "roll dice" button is to be inactivated and
	 * the end turn activated
	 * 
	 * @return playerJailCounter
	 */
	public int getJailCounter() {
		return playerJailCounter;
	}

	/**
	 * method used for increasing or resetting the jailCounter of a entity.player
	 * 
	 * @param amount
	 */
	public void setJailCounter(int amount) {
		this.playerJailCounter = amount;
	}

	/**
	 * Increase number of turns spent in jail by one
	 */
	public void increaseJailCounter() {
		this.playerJailCounter++;
	}

	/**
	 * @param isInJail if entity.player is sent to jail send true, if entity.player is not in jail
	 *                 anymore set to false
	 */
	public void setPlayerIsInJail(boolean isInJail) {
		this.playerIsInJail = isInJail;
	}

	/**
	 * @return Return either true or false if entity.player in in jail or not
	 */
	public Boolean isPlayerInJail() {
		return this.playerIsInJail;
	}

	/**
	 * @return name, the players name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param playerName, the entity.player name to set
	 */
	public void setName(String playerName) {
		this.name = playerName;
	}

	public void setPlayerIndex(int index) {
		this.playerIndex = index;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public int getPosition() {
		return counter;
	}
	public void setPositionInSpecificIndex(int newPosition) {
		this.counter = newPosition;
	}

	public void setPosition(int amountOfStepsToMove) {

		for (int i = 0; i < amountOfStepsToMove; i++) {

			if (counter < 39) {
				counter++;
			} else {
				counter = 0;
				playerPassedgo = true;
			}
		}
	}

	public boolean passedGo() {
		return playerPassedgo;
	}

	public void resetPassedGo() {
		playerPassedgo = false;
	}

	public int getBalance() {
		return this.balance;
	}

	public ImageIcon getImage() {
		return playerIcon;
	}

	public void setBalance(int playerBalance) {
		this.balance = playerBalance;
	}

	public void decreaseBalace(int decrease) {
		this.balance -= decrease;
	}

	public void increaseBalance(int income) {
		this.balance += income;
	}

	public Boolean isAlive() {
		return isAlive;
	}

	public void setIsAlive(Boolean playerIsAlive) {
		this.isAlive = playerIsAlive;
	}

	public String isAliveString() {

		if (isAlive == true) {
			return "This entity.player is alive and well";
		} else
			return "The plauge has taken another soul";
	}

	public PlayerRanks getPlayerRank() {
		return this.playerRank;
	}

	public void setPlayerRank(PlayerRanks playerRank) {
		this.playerRank = playerRank;
	}

	public int getNetWorth() {
		return this.netWorth;
	}

	public void setNetWorth(int netWorth) {
		this.netWorth = netWorth;
	}

	public void decreaseNetWorth(int decrease) {
		this.netWorth -= decrease;
	}

	public void increaseNetWorth(int income) {
		this.netWorth += income;
	}

	public void addNewProperty(Property newProperty) {
		this.propertiesOwned.add(newProperty);
	}

	public void removeProperty(Property property) {

		this.propertiesOwned.remove(property);
		property.setOwner(null);

	}

	public void sellProperty(Property property) {

		int total = (property.getPrice() + (property.getLevel() * property.getLevelPrice()));

		int res = JOptionPane.showConfirmDialog(null,
				"Do you really want to sell " + property.getName() + " for: " + total);

		if (res == 0) {
			increaseBalance(total);
			this.propertiesOwned.remove(property);
			property.setOwner(null);
		}

	}

	public void addNewTavern(Tavern newTavern) {
		this.tavernsOwned.add(newTavern);
	}

	public int getAmountOfTaverns() {
		return tavernsOwned.size();
	}

	public void clearPlayer() {
		for (int i = 0; i < propertiesOwned.size(); i++) {
			propertiesOwned.get(i).clearProperty();

		}
		for (int i = 0; i < tavernsOwned.size(); i++) {
			tavernsOwned.get(i).clearTavern();
		}
	}

	public Property getPropertyAt(int pos)   {

		return this.propertiesOwned.get(pos);
	}

	public Tile getProperty(int pos) {
		return this.propertiesOwned.get(pos);
	}

	public PlayerRanks checkPlayerRank() {

		PlayerRanks rank = null;

		Color RED = new Color(255, 0, 10, 255);
		Color GREEN = new Color(35, 254, 14, 255);
		Color CYAN = new Color(93, 188, 210, 255);
		Color YELLOW = new Color(206, 183, 51, 255);

		if (getNetWorth() <= 1499) {
			if (this.getPlayerColor().equals(RED)) {
				this.playerIcon = RED_PAWN;
			} else if (this.getPlayerColor().equals(GREEN)) {
				this.playerIcon = GREEN_PAWN;
			} else if (this.getPlayerColor().equals(CYAN)) {
				this.playerIcon = BLUE_PAWN;
			} else if (this.getPlayerColor().equals(YELLOW)) {
				this.playerIcon = YELLOW_PAWN;
			}
			rank = PEASANT;
			setPlayerRank(PEASANT);
		}

		if (getNetWorth() >= 1510 && getNetWorth() <= 4000) {
			if (this.getPlayerColor().equals(RED)) {
				this.playerIcon = RED_KNIGHT;
			} else if (this.getPlayerColor().equals(GREEN)) {
				this.playerIcon = GREEN_KNIGHT;
			} else if (this.getPlayerColor().equals(CYAN)) {
				this.playerIcon = BLUE_KNIGHT;
			} else if (this.getPlayerColor().equals(YELLOW)) {
				this.playerIcon = YELLOW_KNIGHT;
			}
			rank = KNIGHT;
			setPlayerRank(KNIGHT);
		}

		if (getNetWorth() >= 4000 && getNetWorth() <= 7500) {
			if (this.getPlayerColor().equals(RED)) {
				this.playerIcon = RED_ROOK;
			} else if (this.getPlayerColor().equals(GREEN)) {
				this.playerIcon = GREEN_ROOK;
			} else if (this.getPlayerColor().equals(CYAN)) {
				this.playerIcon = BLUE_ROOK;
			} else if (this.getPlayerColor().equals(YELLOW)) {
				this.playerIcon = YELLOW_ROOK;
			}
			rank = LORD;
			setPlayerRank(LORD);
		}

		if (getNetWorth() >= 7500) {
			if (this.getPlayerColor().equals(RED)) {
				this.playerIcon = RED_KING;
			} else if (this.getPlayerColor().equals(GREEN)) {
				this.playerIcon = GREEN_KING;
			} else if (this.getPlayerColor().equals(CYAN)) {
				this.playerIcon = BLUE_KING;
			} else if (this.getPlayerColor().equals(YELLOW)) {
				this.playerIcon = YELLOW_KING;
			}
			rank = KING;
			setPlayerRank(KING);
		}

		return rank;
	}

	public ArrayList<Property> getProperties() {
		return this.propertiesOwned;
	}

	public void setPropertiesOwned(ArrayList<Property> propertiesOwned) {
		this.propertiesOwned = propertiesOwned;
	}

	public ArrayList<Tavern> getTaverns() {
		return this.tavernsOwned;
	}

	public Color getPlayerColor() {
		return playerColor;
	}

	public String getPlayerColorText(Color playerColor){
		String color = "";
		Color magenta = new Color(255, 15, 226);
		Color red = new Color(255, 0, 10, 255);
		Color orange = new Color(254, 119, 14, 255);
		Color yellow = new Color(206, 183, 51, 255);
		Color green = new Color(35, 254, 14, 255);
		Color cyan = new Color(93, 188, 210, 255);
		Color purple = Color.decode("#9542f4");


		if (playerColor.equals(magenta)) {
			color = "MAGENTA";
		} else if (playerColor.equals(red)) {
			color = "RED";
		} else if (playerColor.equals(orange)) {
			color = "ORANGE";
		} else if (playerColor.equals(yellow)) {
			color = "YELLOW";
		} else if (playerColor.equals(green)) {
			color = "GREEN";
		} else if (playerColor.equals(cyan)) {
			color = "CYAN";
		} else if (playerColor.equals(purple)) {
			color = "PURPLE";
		}

		return color;
	}
}