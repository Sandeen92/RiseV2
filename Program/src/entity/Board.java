package entity;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utilities.Constants;
import view.GUITile;
import entity.player.Player;
import entity.player.PlayerList;
import entity.tiles.Tile;
import entity.tiles.TileInfo;
import view.WestSidePanel;



/**
 * @author Muhammad Abdulkhuder, Seth �berg, Rohan Samandari
 *
 */
public class Board extends JPanel implements Serializable {
	
	private WestSidePanel pnlWest;
	private TileInfo info = new TileInfo();
	
	private GUITile[] guiTiles = new GUITile[40]; //Creates empty tile objects with a background.
	private PlayerList playerList = new PlayerList();
	private Listener listener = new Listener();
	
	private TileCollection tileCollection = new TileCollection();

	private JPanel[] panelarray = new JPanel[40];

	private JLabel lblNewLabel = new JLabel();

	/**
	 * 
	 * @param wp, WestSidePanel
	 */
	public Board(WestSidePanel wp) {
		this.pnlWest = wp;
		initializeAllPanels();	
		initializeGUI(); 
	}
	
	/**
	 * 
	 * @param playerList, list of players
	 */
	public Board(PlayerList playerList) {
		initializeAllPanels();	
		this.playerList = playerList; 
		
	}

	/**
	 * 
	 * @param playerList, list of players
	 * @param wp, WestSidePanel
	 */
	public Board(PlayerList playerList,WestSidePanel wp) {
		initializeAllPanels();	
		this.playerList = playerList;  
		
	}

	/**
	 * Sets the list of players.
	 * @param playerList
	 */
	public void addPlayers(PlayerList playerList) {
		this.playerList = playerList;
	}
	/**
	 * Sets players
	 */
	public void setPlayers() {
		
		for(int i = 0; i < playerList.getLength(); i++) {
			setPlayer(playerList.getPlayerFromIndex(i));
		}
	}
	
	/**
	 * Initilizes all panels
	 */
	public void initializeAllPanels() {
		setPreferredSize(new Dimension(750, 750));
		setLayout(null);

		int initialX = 649;
		int initialY = 650;
		int width = 101;
		int height = 100;
		int stepX = -62;
		int stepY = -62;

		for (int i = 0; i < panelarray.length; i++) {
			panelarray[i] = new JPanel();
			panelarray[i].setOpaque(false);
			panelarray[i].setBounds(initialX + (i % 11) * stepX, initialY + (i / 11) * stepY, width, height);
			add(panelarray[i]);
			panelarray[i].addMouseListener(listener);
			panelarray[i].setLayout(new BorderLayout());
		}

		lblNewLabel.setBounds(0, -136, 1050, 1022);
		lblNewLabel.setIcon(Constants.BoardImages.BOARD_IMAGE);
		add(lblNewLabel);
	}
	
	/**
	 * Initilizes gui
	 */
	public void initializeGUI() {
		addEmptyGridPanels();
		paintNewBoard(guiTiles); //Requires a array with all 40 entity.tiles to be sent to paintNewBoard.
		
	}
	
	/**
	 * @param index index for a tile.
	 * @return GUITile at given index.
	 */
	public GUITile getTileAtIndex(int index) {
		return guiTiles[index];
	}
	
	
	/**
	 * Tile objects are created with an int depending on their position on the board.
	 */
	public void addEmptyGridPanels() {
		for (int i = 0; i < guiTiles.length; i++) {
			
			/**
			 * If index is between 0-10 then labels are placed in north.
			 */
			if(i < 11) {
				guiTiles[i] = new GUITile(1); 
			}
			
			/**
			 * If index is between 11-20 then labels are placed in east.
			 */
			else if(i < 20) {
				guiTiles[i] = new GUITile(2); 
			}

			/**
			 * If index is between 20 and 30 then labels are placed in south.
			 */
			else if(i < 31) {
				guiTiles[i] = new GUITile(3); 
			}
			
			/**
			 * If index is between 31-39 then labels are placed in west.
			 */
			else if(i < 40) {
				guiTiles[i] = new GUITile(4); 
			}	
		}
	}
		
	/**
	 * Sets a entity.player on a certain position.
	 * @param player
	 */
	public void setPlayer(Player player) {
		guiTiles[player.getPosition()].setPlayer(player); 
	}
	
	/**
	 * Removes a entity.player from a certain position.
	 * @param player
	 */
	public void removePlayer(Player player) {
		guiTiles[player.getPosition()].removePlayer(player);
	}
	
	
	/**
	 * Method is called when the user arrives at the destination tile
	 * @param index of tile.
	 * @return tile at given index.
	 */
	public Tile getDestinationTile(int index) {
		return tileCollection.getTileAtIndex(index);
	}
	 
	/**
	 * Used to initilize all the entity.tiles.
	 * @param spaces, receives a array with all 40 tile objects.
	 */
	public void paintNewBoard(GUITile[] spaces) {

		for(int i = 0; i < panelarray.length; i ++) {
			panelarray[i].add(spaces[i]);
		}	
	}
	
	/**
	 * Listener class for interactive entity.tiles
	 */
	public class Listener implements MouseListener, Serializable {

		/**
		 * Nothing happens when clicking
		 */
		public void mouseClicked(MouseEvent e) {

		}
		/**
		 * Method for when hovering over entity.tiles.
		 */
		public void mouseEntered(MouseEvent e) {
			for (int i=0; i<panelarray.length; i++) {
				if (e.getSource()==panelarray[i]) {
					if (i == 0 || i == 2 || i == 4 || i == 5 ||i == 7 || 
							i == 10 || i == 15 ||i == 17 || 
							i == 20 ||i == 22 || i == 25 || i == 30 ||
							i == 33 ||i == 35 || i == 36 || i == 38){
							pnlWest.setTitleText(info.getInfo(i), info.getTitle(i), Color.DARK_GRAY, Color.white);
					}else if(i==26 || i==27 || i==29) {
						pnlWest.setTitleText(tileCollection.getTileAtIndex(i).getTileInfo(),
								tileCollection.getTileAtIndex(i).getTitle(), new Color(254,231,11, 255), Color.black);
					}
					else if(i == 12 || i == 28) {
						pnlWest.setTitleText(tileCollection.getTileAtIndex(i).getTileInfo(), 
								tileCollection.getTileAtIndex(i).getName(), Color.DARK_GRAY, Color.white);
					}
					else {						
						pnlWest.setTitleText(tileCollection.getTileAtIndex(i).getTileInfo(),
							tileCollection.getTileAtIndex(i).getTitle(), tileCollection.getTileAtIndex(i).getColor(), Color.white );
					}
				}
			}		
			}
		
			/**
			 * Calls method to set default text when mouse leaves a tile.
			 */
			public void mouseExited(MouseEvent e) {
				pnlWest.setTextDefault();
			}

			/**
			 * Nothing happens when mouse is pressed.
			 */
			public void mousePressed(MouseEvent e) {

			}
			
			/**
			 * Nothing happens by releasing mouse.
			 */
			public void mouseReleased(MouseEvent e) {

			}
	}
}
