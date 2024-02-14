package player;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import board.ColorIconMap;
import colorsAndIcons.StringColorMap;
import messageGui.WinGui;
import tiles.Property;
import tiles.Tavern;
import tiles.Tile;

/**
 * Player class deals with everything that has to do with a player.
 * 
 * @author AevanDino, Seth �berg, Muhammad Hasan, Sebastian Viro
 */
public class Player {

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
	 * Constructor for adding a new player, new players are created by the
	 * playerList class and are automatically set at index 0 on the board with the
	 * counter variable set to 0
	 * 
	 * @param inPlayerName chosen Name
	 * @param playerIcon   imageIcon from ColorIconMap
	 * @param playerIndex  index of player (for example if second player the
	 *                     playerIndex is 1)
	 */

	public Player(String inPlayerName, ImageIcon playerIcon, int playerIndex) {

		setName(inPlayerName);
		this.playerIcon = playerIcon;
		setIsAlive(true);
		this.playerIndex = playerIndex;

		setBalance(1500);
		setNetWorth(1500);
		setPlayerRank(playerRank.PEASANT);
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
		setPlayerRank(playerRank.PEASANT);
		this.playerIndex = playerIndex;
		this.tavernsOwned = new ArrayList<>();
		this.propertiesOwned = new ArrayList<>();

		counter = 0;
	}

	/**
	 * Keep track of how many turns a user has been in jail, if 3 the player gets
	 * out of jail if less than 3 the "roll dice" button is to be inactivated and
	 * the end turn activated
	 * 
	 * @return playerJailCounter
	 */
	public int getJailCounter() {
		return playerJailCounter;
	}

	/**
	 * method used for increasing or resetting the jailCounter of a player
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
	 * @param isInJail if player is sent to jail send true, if player is not in jail
	 *                 anymore set to false
	 */
	public void setPlayerIsInJail(boolean isInJail) {
		this.playerIsInJail = isInJail;
	}

	/**
	 * @return Return either true or false if player in in jail or not
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
	 * @param playerName, the player name to set
	 */
	public void setName(String playerName) {
		this.name = playerName;
	}

	/**
	 * Set the playerIndex of the player (the index the user has in the playerList
	 * array)
	 * 
	 * @param index
	 */
	public void setPlayerIndex(int index) {
		this.playerIndex = index;
	}

	/**
	 * @return the playerIndex of a player
	 */
	public int getPlayerIndex() {
		return playerIndex;
	}

	/**
	 * Get the position a player has on the board from 0-39
	 * 
	 * @return counter
	 */
	public int getPosition() {
		return counter;
	}

	/**
	 * Move player to a specific index on the board
	 * 
	 * @param newPosition
	 */
	public void setPositionInSpecificIndex(int newPosition) {
		this.counter = newPosition;
	}

	/**
	 * method used to move the player by either one or many steps
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
	 * @return balance, the player balance
	 */
	public int getBalance() {
		return this.balance;
	}

	/**
	 * @return playerIcon, the image of a player
	 */
	public ImageIcon getImage() {
		return playerIcon;
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
			return "This player is alive and well";
		} else
			return "The plauge has taken another soul";
	}

	/**
	 * @return playerRank the rank of the player
	 */
	public PlayerRanks getPlayerRank() {
		return this.playerRank;
	}

	/**
	 * @param playerRank set the rank of this player
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
 * This method is used to check the player's rank and update the player icon accordingly.
 * 
 * The method checks the player's net worth and sets the player icon to a knight, rook, king, or lord icon depending on the net worth.
 * 
 * The method uses four colors (red, green, cyan, and yellow) to represent the different player ranks. The color of the player is used to determine which rank icon to display.
 * 
 * The method uses the resizeImage method to resize the player icon to a specified width and height.
 * 
 * @return nothing
 */
public void checkPlayerRank() {

    Color RED = new Color(255, 0, 10, 255);
    Color GREEN = new Color(35, 254, 14, 255);
    Color CYAN = new Color(93, 188, 210, 255);
    Color YELLOW = new Color(206, 183, 51, 255);	

    if (getNetWorth() >= 2000) {
        
        if (this.getPlayerColor().equals(RED)) {
            this.playerIcon = resizeImage(new ImageIcon("Program/images/Red_knight.svg.png"));
        }
        else if (this.getPlayerColor().equals(GREEN)) {
            this.playerIcon = resizeImage(new ImageIcon("Program/images/Green_knight.svg.png"));
        }
        else if (this.getPlayerColor().equals(CYAN)) {
            this.playerIcon = resizeImage(new ImageIcon("Program/images/Blue_knight.svg.png"));
        }
        else if (this.getPlayerColor().equals(YELLOW)) {
            this.playerIcon = resizeImage(new ImageIcon("Program/images/Yellow_knight.svg.png"));
        }

        setPlayerRank(PlayerRanks.KNIGHT);
    }

    if (getNetWorth() >= 4000) {

        if (this.getPlayerColor().equals(RED)) {
            this.playerIcon = resizeImage(new ImageIcon("Program/images/Red_rook.svg.png"));
        }
        else if (this.getPlayerColor().equals(GREEN)) {
            this.playerIcon = resizeImage(new ImageIcon("Program/images/Green_rook.svg.png"));
        }
        else if (this.getPlayerColor().equals(CYAN)) {
            this.playerIcon = resizeImage(new ImageIcon("Program/images/Blue_rook.svg.png"));
        }
        else if (this.getPlayerColor().equals(YELLOW)) {
            this.playerIcon = resizeImage(new ImageIcon("Program/images/Yellow_rook.svg.png"));
        }

        setPlayerRank(PlayerRanks.LORD);

    }

    if (getNetWorth() >= 7500) {

        if (this.getPlayerColor().equals(RED)) {
            this.playerIcon = resizeImage(new ImageIcon("Program/images/Red_king.svg.png"));
        }
        else if (this.getPlayerColor().equals(GREEN)) {
            this.playerIcon = resizeImage(new ImageIcon("Program/images/Green_king.svg.png"));
        }
        else if (this.getPlayerColor().equals(CYAN)) {
            this.playerIcon = resizeImage(new ImageIcon("Program/images/Blue_king.svg.png"));
        }
        else if (this.getPlayerColor().equals(YELLOW)) {
            this.playerIcon = resizeImage(new ImageIcon("Program/images/Yellow_king.svg.png"));
        }

        setPlayerRank(PlayerRanks.KINGS);
    }
}

	/**
	 * @return propertiesOwned, returns entire ArrayList of properties owned.
	 */
	public ArrayList<Property> getProperties() {
		return this.propertiesOwned;
	}

	/**
	 * @return all taverns owned by player
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