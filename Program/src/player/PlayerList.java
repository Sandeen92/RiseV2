package player;

import java.awt.Color;
import java.awt.Image;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import board.ColorIconMap;
import colorsAndIcons.StringColorMap;
import eastSidePanels.EastSidePanel;

/**
 * A class that holds all active players 
 * @author Seth Oberg
 *
 */

public class PlayerList {
	private LinkedList<Player> activePlayers = new LinkedList<Player>();
	private ColorIconMap colorIcons = new ColorIconMap();
	private StringColorMap colorMap = new StringColorMap();
	
	private ImageIcon playerIcon = new ImageIcon(); 
	private int currentPlayer = 0; 
	private int playerListLength = 0;
	
	
	/**
	 * Constructor that sets the active player to 0 immediately at the start of a game
	 * @param p
	 */
	public PlayerList(EastSidePanel p) {
		currentPlayer = 0;  
		
	}
	
	/**
	 * Constructor that sets the active player to 0 immediately at the start of a game
	 */
	public PlayerList() {
		currentPlayer = 0; 
	}
	
	/**
	 * Add new player
	 * @param name the chosen name for a player
	 * @param icon the chosen image for a player
	 */
	public void addNewPlayer(String name, ImageIcon icon) {
		activePlayers.add(new Player(name, icon, playerListLength)); 
		playerListLength++;
	} 
		
	/**
	 * Adds new player with the use of the ColorIconMap
	 * @param name chosen name
	 * @param icon string containing a color used to get a color from the ColorIconMap
	 */
	public void addNewPlayer(String name, String icon) {
		playerIcon = colorIcons.getColorFromMap(icon);
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
	 * @param index get specific player
	 * @return player at chosen index
	 */
	public Player getPlayerFromIndex(int index) {
		return activePlayers.get(index);
	}
	
	/**
	 * @return the current player
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
	 * Update amount of players after a player has been removed
	 */
	public void updatePlayerList() {
		
		for(int i = 0; i < activePlayers.size(); i++) {
			activePlayers.get(i).setPlayerIndex(i);
		}	
	}
	
	/**
	 * Used to switch to the current player to the next one
	 */
	public void switchToNextPlayer() {
		
		if(currentPlayer < (activePlayers.size() - 1) ) {
			currentPlayer = currentPlayer + 1;
		}
		else {
			currentPlayer = 0; 
		}	
	}

	/*public void changePlayerIcon() {

		for (int i = 0; i < activePlayers.size(); i++) {
		
			if (activePlayers.get(i).getPlayerRank().equals(PlayerRanks.KNIGHT) 
				&& activePlayers.get(i).getPlayerColor().equals(Color.RED)){ 
				
					ImageIcon knightIcon = resizeImage(new ImageIcon("Program/images/Red_knight.svg.png"));
					activePlayers.get(i).setPlayerIcon(knightIcon);
			}
			else if (activePlayers.get(i).getPlayerRank().equals(PlayerRanks.KNIGHT) 
                && activePlayers.get(i).getPlayerColor().equals(Color.CYAN)){ 

					ImageIcon knightIcon = resizeImage(new ImageIcon("Program/images/Blue_knight.svg.png"));
					activePlayers.get(i).setPlayerIcon(knightIcon);
			} 
			else if (activePlayers.get(i).getPlayerRank().equals(PlayerRanks.KNIGHT) 
                && activePlayers.get(i).getPlayerColor().equals(Color.GREEN)){ 

					ImageIcon knightIcon = resizeImage(new ImageIcon("Program/images/Green_knight.svg.png"));
					activePlayers.get(i).setPlayerIcon(knightIcon);
				}
				else if (activePlayers.get(i).getPlayerRank().equals(PlayerRanks.KNIGHT) 
                && activePlayers.get(i).getPlayerColor().equals(Color.YELLOW)){  
					
					ImageIcon knightIcon = resizeImage(new ImageIcon("Program/images/Yellow_knight.svg.png"));
					activePlayers.get(i).setPlayerIcon(knightIcon);
				}				
			}
		}

	private static ImageIcon resizeImage(ImageIcon originalIcon) {
		Image originalImage = originalIcon.getImage();
		int width = 40;
		int height = 40;
		Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}*/
}
