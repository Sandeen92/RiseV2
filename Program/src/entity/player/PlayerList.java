package entity.player;

import java.io.Serializable;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import entity.StringColorMap;
import utilities.Constants;
import view.eastSidePanels.EastSidePanel;

/**
 * A class that holds all active players 
 * @author Seth Oberg
 *
 */

public class PlayerList implements Serializable {
	private LinkedList<Player> activePlayers = new LinkedList<Player>();
	private StringColorMap colorMap = new StringColorMap();
	private ImageIcon playerIcon = new ImageIcon(); 
	private int currentPlayer = 0; 
	private int playerListLength = 0;
	
	
	/**
	 * Constructor that sets the active entity.player to 0 immediately at the start of a game
	 * @param p
	 */
	public PlayerList(EastSidePanel p) {
		currentPlayer = 0;  
	}
	
	/**
	 * Constructor that sets the active entity.player to 0 immediately at the start of a game
	 */
	public PlayerList() {
		currentPlayer = 0; 
	}
	
	/**
	 * Add new entity.player
	 * @param name the chosen name for a entity.player
	 * @param icon the chosen image for a entity.player
	 */
	public void addNewPlayer(String name, ImageIcon icon) {
		activePlayers.add(new Player(name, icon, playerListLength)); 
		playerListLength++;
	} 
		
	/**
	 * Adds new entity.player with the use of the ColorIconMap
	 * @param name chosen name
	 * @param icon string containing a color used to get a color from the ColorIconMap
	 */
	public void addNewPlayer(String name, String icon) {
		playerIcon = colorMap.getImageIconFromMap(icon);
		activePlayers.add(new Player(name, playerIcon, colorMap.getColorFromMap(icon), playerListLength)); 
		playerListLength++;
	}
	
	/**
	 * @return list with all players
	 */
	public PlayerList getList() {
		return this;
	}
	
	/**
	 * @param index get specific entity.player
	 * @return entity.player at chosen index
	 */
	public Player getPlayerFromIndex(int index) {
		return activePlayers.get(index);
	}
	
	/**
	 * @return the current entity.player
	 */
	public Player getActivePlayer() {
		return activePlayers.get(currentPlayer); 
	}
		
	/**
	 * @return amount of players
	 */
	public int getLength() {
		return activePlayers.size();
	}
		
	/**
	 * @param player to remove from list 
	 */
	public void eliminatePlayer(Player player) {
		player.clearPlayer(); 
		activePlayers.remove(player.getPlayerIndex());
	}
		
	/**
	 * Update amount of players after a entity.player has been removed
	 */
	public void updatePlayerList() {
		
		for(int i = 0; i < activePlayers.size(); i++) {
			activePlayers.get(i).setPlayerIndex(i);
		}	
	}
	
	/**
	 * Used to switch to the current entity.player to the next one
	 */
	public void switchToNextPlayer() {
		
		if(currentPlayer < (activePlayers.size() - 1) ) {
			currentPlayer = currentPlayer + 1;
		}
		else {
			currentPlayer = 0; 
		}	
	}
}