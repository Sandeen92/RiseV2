package entity.player;

import java.awt.*;
import java.io.Serializable;
import java.util.LinkedList;

import entity.StringColorMap;

/**
 * A class that holds all active players 
 * @author Seth Oberg
 *
 */

public class PlayerList implements Serializable {
	private LinkedList<Player> players;
	private StringColorMap colorMap;
	private int currentPlayer = 0; 
	private int playerListLength = 0;
	

	public PlayerList() {
		players = new LinkedList<Player>();
		colorMap = new StringColorMap();
	}


	public void addNewPlayer(String name, String icon) {
		players.add(new Player(name, colorMap.getImageIconFromMap(icon), colorMap.getColorFromMap(icon), playerListLength));
		playerListLength++;
	}

	public Player getPlayerFromIndex(int index) {
		return players.get(index);
	}

	public Player getActivePlayer() {
		return players.get(currentPlayer);
	}

	public int getLength() {
		return players.size();
	}

	public void eliminatePlayer(Player player) {
		player.clearPlayer(); 
		players.remove(player.getPlayerIndex());
	}

	public void updatePlayerList() {
		
		for(int i = 0; i < players.size(); i++) {
			players.get(i).setPlayerIndex(i);
		}	
	}

	public int getActivePlayerIndex(Player player) {
		return player.getPlayerIndex();
	}

	public void switchToNextPlayer() {
		if(currentPlayer < (players.size() - 1) ) {
			currentPlayer = currentPlayer + 1;
		}
		else {
			currentPlayer = 0; 
		}	
	}
}
