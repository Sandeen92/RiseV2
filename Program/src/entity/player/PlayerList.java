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
	

	public PlayerList(EastSidePanel p) {
		currentPlayer = 0;  
	}

	public PlayerList() {
		currentPlayer = 0; 
	}

	public void addNewPlayer(String name, ImageIcon icon) {
		activePlayers.add(new Player(name, icon, playerListLength)); 
		playerListLength++;
	} 

	public void addNewPlayer(String name, String icon) {
		playerIcon = colorMap.getImageIconFromMap(icon);
		activePlayers.add(new Player(name, playerIcon, colorMap.getColorFromMap(icon), playerListLength)); 
		playerListLength++;
	}

	public Player getPlayerFromIndex(int index) {
		return activePlayers.get(index);
	}

	public Player getActivePlayer() {
		return activePlayers.get(currentPlayer); 
	}

	public int getLength() {
		return activePlayers.size();
	}

	public void eliminatePlayer(Player player) {
		player.clearPlayer(); 
		activePlayers.remove(player.getPlayerIndex());
	}

	public void updatePlayerList() {
		
		for(int i = 0; i < activePlayers.size(); i++) {
			activePlayers.get(i).setPlayerIndex(i);
		}	
	}

	public void switchToNextPlayer() {
		
		if(currentPlayer < (activePlayers.size() - 1) ) {
			currentPlayer = currentPlayer + 1;
		}
		else {
			currentPlayer = 0; 
		}	
	}
}
