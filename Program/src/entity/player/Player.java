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

	/**
	 * Constructor for adding a new entity.player, new players are created by the
	 * playerList class and are automatically set at index 0 on the board with the
	 * counter variable set to 0
	 * 
	 * @param inPlayerName chosen Name
	 * @param playerIcon   imageIcon from ColorIconMap
	 * @param playerIndex  index of entity.player (for example if second entity.player the
	 *                     playerIndex is 1)
	 */

	public Player(String inPlayerName, ImageIcon playerIcon, int playerIndex) {

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

	/**
	 * Set the playerIndex of the entity.player (the index the user has in the playerList
	 * array)
	 * 
	 * @param index
	 */
	public void setPlayerIndex(int index) {
		this.playerIndex = index;
	}

	/**
	 * @return the playerIndex of a entity.player
	 */
	public int getPlayerIndex() {
		return playerIndex;
	}

	/**
	 * Get the position a entity.player has on the board from 0-39
	 * 
	 * @return counter
	 */
	public int getPosition() {
		return counter;
	}

	/**
	 * Move entity.player to a specific index on the board
	 * 
	 * @param newPosition
	 */
	public void setPositionInSpecificIndex(int newPosition) {
		this.counter = newPosition;
	}

	/**
	 * method used to move the entity.player by either one or many steps
	 * 
	 * @param amountOfStepsToMove
	 */
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

	/**
	 * @return playerPassedgo, boolean to keep track if user has passed go
	 */
	public boolean passedGo() {
		return playerPassedgo;
	}

	/**
	 * reset has passedGo variable to false
	 */
	public void resetPassedGo() {
		playerPassedgo = false;
	}

	/**
	 * @return balance, the entity.player balance
	 */
	public int getBalance() {
		return this.balance;
	}

	/**
	 * @return playerIcon, the image of a entity.player
	 */
	public ImageIcon getImage() {
		return playerIcon;
	}
	public void setImage(ImageIcon playerIcon){
		this.playerIcon = playerIcon;
	}
	/**
	 * @param playerBalance the playerBalance to set
	 */
	public void setBalance(int playerBalance) {
		this.balance = playerBalance;
	}

	/**
	 * @param decrease amount to decrease players balance by
	 */
	public void decreaseBalace(int decrease) {
		this.balance -= decrease;
	}

	/**
	 * @param income increase players balance by an amount
	 */
	public void increaseBalance(int income) {
		this.balance += income;
	}

	/**
	 * @return the playerIsAlive
	 */
	public Boolean isAlive() {
		return isAlive;
	}

	/**
	 * @param playerIsAlive the playerIsAlive to set
	 */
	public void setIsAlive(Boolean playerIsAlive) {
		this.isAlive = playerIsAlive;
	}

	public String isAliveString() {

		if (isAlive == true) {
			return "This entity.player is alive and well";
		} else
			return "The plauge has taken another soul";
	}

	/**
	 * @return playerRank the rank of the entity.player
	 */
	public PlayerRanks getPlayerRank() {
		return this.playerRank;
	}

	/**
	 * @param playerRank set the rank of this entity.player
	 */
	public void setPlayerRank(PlayerRanks playerRank) {
		this.playerRank = playerRank;
	}

	/**
	 * @return the netWorth
	 */
	public int getNetWorth() {
		return this.netWorth;
	}

	/**
	 * @param netWorth the netWorth to set
	 */
	public void setNetWorth(int netWorth) {
		this.netWorth = netWorth;
	}

	/**
	 * @param decrease
	 */
	public void decreaseNetWorth(int decrease) {
		this.netWorth -= decrease;
	}

	/**
	 * @param income
	 */
	public void increaseNetWorth(int income) {
		this.netWorth += income;
	}

	/**
	 * Adds newly purchased property to ownedProperties array
	 * 
	 * @param newProperty, the newly bought property.
	 */
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

	/**
	 * @param newTavern add a new Tavern to a user
	 */
	public void addNewTavern(Tavern newTavern) {
		this.tavernsOwned.add(newTavern);
	}

	/**
	 * If user has two taverns the event will differ
	 * 
	 * @return amount of taverns
	 */
	public int getAmountOfTaverns() {
		return tavernsOwned.size();
	}

	/**
	 * If user is eliminated reset all users properties and taverns by setting the
	 * amount of houses to 0 and remove the owner
	 */
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

	/**
	 * Gets property at specified position
	 * 
	 * @param pos
	 * @return
	 */
	public Tile getProperty(int pos) {
		return this.propertiesOwned.get(pos);
	}

	/**
 * This method is used to check the entity.player's rank and update the entity.player icon accordingly.
 * 
 * The method checks the entity.player's net worth and sets the entity.player icon to a peasant, knight, rook, lord, or king icon depending on the net worth.
 * 
 * The method uses four colors (red, green, cyan, and yellow) to represent the different entity.player ranks. The color of the entity.player is used to determine which rank icon to display.
 * 
 * The method uses the resizeImage method to resize the entity.player icon to a specified width and height.
 * 
 * @return nothing
 */
	public PlayerRanks checkPlayerRank() {

		Color RED = new Color(255, 0, 10, 255);
		Color GREEN = new Color(35, 254, 14, 255);
		Color CYAN = new Color(93, 188, 210, 255);
		Color YELLOW = new Color(206, 183, 51, 255);

		if (getNetWorth() <= 1499) {
			if (this.getPlayerColor().equals(RED)) {
				setImage(RED_PAWN);
			} else if (this.getPlayerColor().equals(GREEN)) {
				setImage(GREEN_PAWN);
			} else if (this.getPlayerColor().equals(CYAN)) {
				setImage(CYAN_PAWN);
			} else if (this.getPlayerColor().equals(YELLOW)) {
				setImage(YELLOW_PAWN);
			}
			playerRank = PEASANT;
			setPlayerRank(PEASANT);
		}

		if (getNetWorth() >= 1510 && getNetWorth() <= 4000) {
			if (this.getPlayerColor().equals(RED)) {
				setImage(RED_KNIGHT);
			} else if (this.getPlayerColor().equals(GREEN)) {
				setImage(GREEN_KNIGHT);
			} else if (this.getPlayerColor().equals(CYAN)) {
				setImage(CYAN_KNIGHT);
			} else if (this.getPlayerColor().equals(YELLOW)) {
				setImage(YELLOW_KNIGHT);
			}
			playerRank = KNIGHT;
			setPlayerRank(KNIGHT);
		}

		if (getNetWorth() >= 4000 && getNetWorth() <= 7500) {
			if (this.getPlayerColor().equals(RED)) {
				setImage(RED_ROOK);
			} else if (this.getPlayerColor().equals(GREEN)) {
				setImage(GREEN_ROOK);
			} else if (this.getPlayerColor().equals(CYAN)) {
				setImage(CYAN_ROOK);
			} else if (this.getPlayerColor().equals(YELLOW)) {
				setImage(YELLOW_ROOK);
			}
			playerRank = LORD;
			setPlayerRank(LORD);
		}

		if (getNetWorth() >= 7500) {
			if (this.getPlayerColor().equals(RED)) {
				setImage(RED_KING);
			} else if (this.getPlayerColor().equals(GREEN)) {
				setImage(GREEN_KING);
			} else if (this.getPlayerColor().equals(CYAN)) {
				setImage(CYAN_KING);
			} else if (this.getPlayerColor().equals(YELLOW)) {
				setImage(YELLOW_KING);
			}
			playerRank = KING;
			setPlayerRank(KING);
		}



		return playerRank;
	}

	/**
	 * @return propertiesOwned, returns entire ArrayList of properties owned.
	 */
	public ArrayList<Property> getProperties() {
		return this.propertiesOwned;
	}

	/**
	 * @return all taverns owned by entity.player
	 */
	public ArrayList<Tavern> getTaverns() {
		return this.tavernsOwned;
	}

	/**
	 * Returns the players color
	 * 
	 * @return playerColor
	 */
	public Color getPlayerColor() {
		return playerColor;
	}

	    /**
     * Resizes an image icon to a specified width and height, using the specified scaling algorithm.
     * 
     * @param originalIcon the original image icon to be resized
     * @return a new image icon with the specified dimensions and the same image as the original icon
     */
    public static ImageIcon resizeImage(ImageIcon originalIcon) {
        Image originalImage = originalIcon.getImage();
        int width = 40; // specify the desired width and height
        int height = 40;
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

}