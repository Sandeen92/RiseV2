package view;

import java.awt.Color;

import java.awt.Dimension;
import java.io.Serializable;


import javax.swing.*;
import javax.swing.border.*;

import controller.BoardController;
import entity.player.Player;
import entity.player.PlayerList;
import utilities.Constants;
import view.eastSidePanels.EastPanel;
import utilities.BackgroundMusic;
import view.messageGui.Menu;

/**
 * This class combines most of the panels in the game and adds appropriate
 * references.
 * 
 * @author Abdulkhuder Muhammad
 *
 */
public class MainPanel extends JPanel implements Serializable {

	private BoardController boardController;
	private EastPanel eastPanel;
	private WestPanel westPanel;
	private BoardPanel boardPanel;
	private DicePanel dicePanel;
	private Dimension screenSize;
	private JFrame frame;
	private JLabel lblPic;
	private Menu menu;
	private int screenWidth;
	private int screenHeight;

	/**
	 * adds the panels and sets the bounds
	 */
	public MainPanel() {
		boardController = new BoardController(this);
		eastPanel = new EastPanel();
		westPanel = new WestPanel(boardController);
		boardPanel = new BoardPanel(boardController);
		dicePanel = new DicePanel(boardController);
		setUpMainFrame();
	}

	public void setUpMainFrame(){
		lblPic = new JLabel();
		setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.BLACK));

		setBackground(Color.DARK_GRAY);

		setLayout(null);
		eastPanel.setOpaque(false);
		eastPanel.setBounds(1095, 0, 345, 860);
		add(eastPanel);
		westPanel.setBounds(0, 0, 345, 860);
		add(westPanel);
		boardPanel.setBounds(346, 0, 750, 750);
		add(boardPanel);
		dicePanel.setBounds(346, 751, 750, 109);
		add(dicePanel);
		menu = new Menu(new BackgroundMusic(), this);
		menu.setBounds(0, 0, 50, 18);
		add(menu);
		setScreenSize(Constants.GameWindow.screenSize);
		repaint();
	}

	public void setScreenSize(Dimension screenSize) {
		this.screenSize = screenSize;
		screenWidth = screenSize.width;
		screenHeight = screenSize.height;
		setPreferredSize(new Dimension(screenWidth, screenHeight));

		lblPic.setBounds(0, 0, screenWidth, screenHeight);

		lblPic.setIcon(Constants.BoardImages.getBackgroundImage());
		add(lblPic);
		repaint();
	}


	public void startboard() {
		frame = new JFrame("Change your fate");
		frame.setPreferredSize(new Dimension(screenWidth + 18, screenHeight + 10));
		frame.setLocation(-9, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().add(this);
		frame.pack();
		repaint();
	}

	public void setDiceImages(ImageIcon dice1, ImageIcon dice2) {
		dicePanel.setDiceImages(dice1, dice2);
	}

	public void enableEndTurnBtn() {
		dicePanel.enableEndTurnBtn();
	}

	public void removePlayer(Player player) {
        boardPanel.removePlayer(player);
    }

	public void updateTurnLabel(String playerName, Color color) {
		dicePanel.updateTurnLabel(playerName, color);
	}

	public void setTitleText(String info, String lblTitle, Color titleColor, Color titleTxtColor) {
		westPanel.setTitleText(info, lblTitle, titleColor, titleTxtColor);
	}

	public void setMarker(Player player,int i){
		boardPanel.setMarker(player,i);
	}



	public String addPlayerToList(String name, String icon) {
		boardController.addPlayerToList(name, icon);
		return name;
	}

	public void movePlayerOnBoard(Player player) {
		boardPanel.movePlayerOnBoard(player);
	}

	public void setPlayerToTile(Player player) {
		boardPanel.setPlayerToTile(player);
	}

	public void setTextDefault() {
        westPanel.setTextDefault();
    }

	public void addPlayerTabs() {
		PlayerList playerList = boardController.getPlayerList();
		eastPanel.addPlayerList(playerList);
		boardPanel.setPlayerIndexes(playerList);
		updateTurnLabel(playerList.getPlayerFromIndex(0).getName(), playerList.getPlayerFromIndex(0).getPlayerColor());
	}

	public void updatePlayerTabs(){
		PlayerList playerList = boardController.getPlayerList();
		eastPanel.addPlayerList(playerList);
		boardPanel.setPlayerIndexes(playerList);
	}

	public void removeEventFromPanel(){
		westPanel.resetEventPanel();
	}

	public void appendWestPanel(String text) {
        westPanel.append(text);
    }
	public EventPanel getEventPanel(){
        return westPanel.getEventPanel();
    }

	public void Dispose() {
		frame.dispose();
	}

	public WestPanel getWestPanel(){
		return westPanel;
	}
}
